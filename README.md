# Legends Browser #

Legends Browser is an multi-platform, open source, java-based legends viewer for dwarf fortress 0.42.

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

### Important Note ###

some features require the legends_plus.xml from dfhack (run 'exportlegends info')
some features require also an extended exportlegends script for dfhack
you can find the latest version here: https://raw.githubusercontent.com/robertjanetzko/dfhack/develop/scripts/exportlegends.lua
place it in hack/scripts of your dwarf fortress installation

### Troubleshooting ###

* Development is still in a very early stage, so you may find bugs
* If you find any bugs, feel free to open an issue here on github
* If you have questions there is a forum thread http://www.bay12forums.com/smf/index.php?topic=155307.0