# PebbleKit

This plugin provides a cordova plugin interface for [PebbleKit](https://github.com/pebble/pebblekit)

It's not all complete, but I'm working on it.

## Installation

`cordova plugin add https://github.com/konsumer/phonegap-pebble.git`

## Supported Platforms

- Android
- iOs (planned)


## Usage

Coming soon.

## Making Pebbble Apps

There are 2 parts to a potential Pebble app: on-Pebble watchface/app & on-phone. This project only deals with the on-phone part, but I will talk about options for both.


### on-Pebble

This is the actual thing on the Pebble that makes the watchface, runs as app, & interacts with phone (if needed.) You can do it in a couple ways:


#### [PebbleC](http://developer.getpebble.com/docs/c/)

Use this if you are comfortable with C.

**pros**

- very fast
- full API - you can do everything a Pebble can do

**cons**

- trickier to use than javascript
- brain has to switch around if you are using javascript for phone-ui
- lower-level than javascript


#### [Pebble.js](http://developer.getpebble.com/docs/pebblejs)

Use this if you want to keep everything in javascript and don't need anything that's C-only.

**pros**

- very easy to use
- same programming-langage as phone-side, if you are using cordova

**cons**

- many things in C API are not implemented.


### on-Phone

This is how you leverage the capabilities of your smart-phone, like internet, increased storage, GPS, etc. You can do this 3 different ways:


#### [PebbleKit JS](http://developer.getpebble.com/docs/js/)

Use this if you just need simple interface to basic phone capabilities.

**pros**

- no install on the phone (uses Pebble app, which is required by everything else anyway)
- 'settings' in Pebble app links to your own HTML page
- very easy

**cons**

- incredibly limited subset of API.


#### this project

Use this if you need more than PebbleKitJS (in the form of a companion-app) & prefer the cordova way of doing things.

**pros**

- same code works on iOS & Android
- simple web technology (js, css, html,) easier if you are already familiar
- full native API implemented
- lots of other libraries available for cordova to do other things

**cons**

- less performant than native


#### native [Android](http://developer.getpebble.com/docs/android/) / [iOS](http://developer.getpebble.com/docs/ios/)

Use this if you need more than PebbleKitJS (in the form of a companion-app) & don't need to support multiple platforms.

**pros**

- slightly more performant than this project for a few things

**cons**

- have to make new apps for every supported platform
