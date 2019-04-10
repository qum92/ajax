/**
 * 
 */
var AjaxUtil = function() {
	var xhr = new XMLHttpRequest();
	this.open = function(config) {
		config.method = config.method ? config.method : 'GET';
		config.async = config.async ? config.async : true;
		xhr.open(config.method, config.url);
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					this.callback(xhr.response);
				}
			}
		}
	}
	xhr.callback = config.callback
}
this.send = function() {
	xhr.send();
}
