import _ from 'lodash';
import sjs from 'servicejs';
import srvdjs from 'servedjs';

function component() {
  let element = document.createElement('div');

  element.innerHTML = _.join(['Hello', 'webpack'], ' ');

  return element;
}

document.body.appendChild(component());

