# Legends Browser #

Legends Browser is an multi-platform, open source, java-based legends viewer for dwarf fortress 0.44.

### Features ###

* Works in the browser of your choice (just launch an open http://localhost:58881)
* Recreates Legends Mode from dwarf fortress, with objects beeing accessible as links
* Add several statistics and overviews
* supports LNP processed and archived legends_archive.zip

### Using Legends Browser ###

* Download the latest release from the downloads page https://github.com/robertjanetzko/LegendsBrowser/releases
* Run the application
* On Windows run the .exe
* On MacOS open the .dmg and run the application
* On other operating systems run java -jar legendsbrowser-x.x.x.jar
* An browser window should open, if not navigate to http://localhost:58881
* Open a legends export by navigating your file system
* ready to load exports should show up in green
* after loading finished you should see an overview over all civilizations

### Command Line Options ###

```
-p,--port <arg>     use specific port
-s,--serverMode     run in server mode (disables file chooser)
-u,--subUri <arg>   run on /<subUri>
-w,--world <arg>    path to legends.xml or archive
```

### Important Note ###

* some features require the legends_plus.xml from dfhack (run 'exportlegends info')
* some features require also an extended exportlegends script for dfhack
* you can find the latest version here: https://raw.githubusercontent.com/robertjanetzko/dfhack/develop/scripts/exportlegends.lua
* place it in hack/scripts of your dwarf fortress installation

### OutOfMemoryError ###

* some legends export can be very large (over 400MB) and require approx twice the amount of memory when loaded
* if you get an OutOfMemoryError displayed in the console you can manually increase the amount of memory legends browser gets assigned
* to do so create an file called LegendsBrowser-X.X.X.l4j.ini (the name of your .exe where you relplace the ending with .l4j.ini)
* open that file in an editor and insert -Xmx1024M (you can change the numbers to set the amount of memory you want to assign)

### Troubleshooting ###

* Development is still in a very early stage, so you may find bugs
* If you find any bugs, feel free to open an issue here on github
* If you have questions there is a forum thread http://www.bay12forums.com/smf/index.php?topic=155307.0
