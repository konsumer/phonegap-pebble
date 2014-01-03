# Phonegap Pebble

A Phonegap interface to PebbleKit

This is a stub. Still working out details.

## Integration

*  Download [Pebble SDK](https://developer.getpebble.com/2/getting-started/) and integrate with project
*  Add PebblePGPlugin.java to your project, in src/com/phonegap/pebble/.
*  Copy PebblePGPlugin.js to your project's www/js folder.
*  Add the following to res/xml/config.xml for PhoneGap version 3.0+:

```xml
<feature name="Pebble">
   <param name="android-package" value="com.phonegap.pebble.PebblePGPlugin" />
 </feature>
```

for older versions under the plugins tag:

```xml
<plugin name="Pebble" value="com.phonegap.pebble.PebblePGPlugin" />
```