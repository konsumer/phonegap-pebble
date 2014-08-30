#include <pebble.h>

static Window *window;
static TextLayer *desc_text;
static TextLayer *alphabet_text;
	
// Write message to buffer & send
void send_message(const char *letter){
	DictionaryIterator *iter;
	
	app_message_outbox_begin(&iter);
	dict_write_cstring(iter, 0, letter);
	
	dict_write_end(iter);
  	app_message_outbox_send();
}

// http://stackoverflow.com/questions/21150193/logging-enums-on-the-pebble-watch/21172222#21172222
char *translate_error(AppMessageResult result) {
  switch (result) {
    case APP_MSG_OK: return "APP_MSG_OK";
    case APP_MSG_SEND_TIMEOUT: return "APP_MSG_SEND_TIMEOUT";
    case APP_MSG_SEND_REJECTED: return "APP_MSG_SEND_REJECTED";
    case APP_MSG_NOT_CONNECTED: return "APP_MSG_NOT_CONNECTED";
    case APP_MSG_APP_NOT_RUNNING: return "APP_MSG_APP_NOT_RUNNING";
    case APP_MSG_INVALID_ARGS: return "APP_MSG_INVALID_ARGS";
    case APP_MSG_BUSY: return "APP_MSG_BUSY";
    case APP_MSG_BUFFER_OVERFLOW: return "APP_MSG_BUFFER_OVERFLOW";
    case APP_MSG_ALREADY_RELEASED: return "APP_MSG_ALREADY_RELEASED";
    case APP_MSG_CALLBACK_ALREADY_REGISTERED: return "APP_MSG_CALLBACK_ALREADY_REGISTERED";
    case APP_MSG_CALLBACK_NOT_REGISTERED: return "APP_MSG_CALLBACK_NOT_REGISTERED";
    case APP_MSG_OUT_OF_MEMORY: return "APP_MSG_OUT_OF_MEMORY";
    case APP_MSG_CLOSED: return "APP_MSG_CLOSED";
    case APP_MSG_INTERNAL_ERROR: return "APP_MSG_INTERNAL_ERROR";
    default: return "UNKNOWN ERROR";
  }
}

// Called when a message is received from phone-app
// when I recieve a message, send the same letter back
static void in_received_handler(DictionaryIterator *received, void *context) {
	Tuple *tuple;

	int x;
	for ( x = -1; x < 100; x++ ) {
		tuple = dict_find(received, 0);
		if(tuple) {
			APP_LOG(APP_LOG_LEVEL_DEBUG, "received letter: %s", tuple->value->cstring);
			text_layer_set_text(alphabet_text, tuple->value->cstring);
			send_message(tuple->value->cstring);
		}
	}
}

// Called when an incoming message from phone-app is dropped
static void in_dropped_handler(AppMessageResult reason, void *context) {
	APP_LOG(APP_LOG_LEVEL_DEBUG, "dropped: %s", translate_error(reason));
}

// Called when phone-app does not acknowledge receipt of a message
static void out_failed_handler(DictionaryIterator *failed, AppMessageResult reason, void *context) {
	APP_LOG(APP_LOG_LEVEL_DEBUG, "failed: %s", translate_error(reason));
}


int main( void ) {
	window = window_create();
	window_stack_push(window, true);
	
	// Register AppMessage handlers
	app_message_register_inbox_received(in_received_handler); 
	app_message_register_inbox_dropped(in_dropped_handler); 
	app_message_register_outbox_failed(out_failed_handler);
		
	app_message_open(app_message_inbox_size_maximum(), app_message_outbox_size_maximum());

	desc_text = text_layer_create(GRect(0, 0, 144, 16));
	text_layer_set_text_alignment(desc_text, GTextAlignmentCenter);
  	layer_add_child(window_get_root_layer(window), text_layer_get_layer(desc_text));
  	text_layer_set_text(desc_text, "current letter (phone)");

  	alphabet_text = text_layer_create(GRect(0, 18, 144, 50));
	text_layer_set_text_alignment(alphabet_text, GTextAlignmentCenter);
	text_layer_set_font(alphabet_text, fonts_get_system_font(FONT_KEY_BITHAM_42_BOLD));
	layer_add_child(window_get_root_layer(window), text_layer_get_layer(alphabet_text));
	text_layer_set_text(alphabet_text, "*");

	app_event_loop();
	
	app_message_deregister_callbacks();
	window_destroy(window);
}