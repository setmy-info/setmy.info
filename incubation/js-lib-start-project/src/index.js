import _ from 'lodash';
import sjs from 'servicejs';
import srvdjs from 'servedjs';

function component() {
  let element = document.createElement('div');
  var geoWatcher = jsdi.get().$geo.newWatcher(function (position) {console.log("Position: ", position);});

  element.innerHTML = _.join(['Hello', 'webpack'], ' ');

  return element;
}

document.body.appendChild(component());

