# ![alt tag](./docs/images/logo-cyfluxviz.png) cy3fluxviz

<a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&amp;hosted_button_id=RYHNRJFBMWD5N" title="Donate to this project using Paypal"><img src="https://img.shields.io/badge/paypal-donate-yellow.svg" alt="PayPal donate button" /></a> [![Build Status](https://travis-ci.org/matthiaskoenig/cy3fluxviz.svg?branch=develop)](https://travis-ci.org/matthiaskoenig/cy3fluxviz)

**cy3fluxviz** is an open-source Cytoscape 3 app for the visualization of flux distributions in molecular interaction  networks. This project will port [cy2fluxviz](https://github.com/matthiaskoenig/cy2fluxviz) to Cytoscape 3.

[![Download](docs/images/icon-download.png) Download](https://github.com/matthiaskoenig/cy3fluxviz/releases/latest)  
**Status** : alpha  
**Support & Forum** : https://groups.google.com/forum/#!forum/cysbml-cyfluxviz  
**Bug Tracker** : https://github.com/matthiaskoenig/cy3fluxviz/issues  

## License
* Source Code: [GPLv3](http://opensource.org/licenses/GPL-3.0)
* Documentation: [CC BY-SA 4.0](http://creativecommons.org/licenses/by-sa/4.0/)

## Installation
### Requirements
* Java 8
* Cytoscape >= 3.3.0
* [cy3sbml](https://github.com/matthiaskoenig/cy3sbml/)

### Install
* Download the latest cy3fluxviz release `cy3fluxviz-vx.x.x.jar`  
  https://github.com/matthiaskoenig/cy3fluxviz/releases/latest
* In Cytoscape open the app manager via `Apps → App Manager → Install Apps` and click `Install from File`. Select the downloaded jar.

### Build instructions
For working with the latest **development release** checkout the `develop` branch and build with maven
```
git clone https://github.com/matthiaskoenig/cy3fluxviz.git cy3fluxviz
cd cy3fluxviz
git checkout -b develop --track origin/develop
mvn clean install
```

## Changelog
**v0.0.2**
* basic integration with cy3sbml
