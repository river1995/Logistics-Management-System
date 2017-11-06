var DataSourceTree = function(options) {
	this._data 	= options.data;
	this._delay = options.delay;
}

DataSourceTree.prototype.data = function(options, callback) {
	var self = this;
	var $data = null;

	if(!("name" in options) && !("type" in options)){
		$data = this._data;//the root tree
		callback({ data: $data });
		return;
	}
	else if("type" in options && options.type == "folder") {
		if("additionalParameters" in options && "children" in options.additionalParameters)
			$data = options.additionalParameters.children;
		else $data = {}//no data
	}
	
	if($data != null)//this setTimeout is only for mimicking some random delay
		setTimeout(function(){callback({ data: $data });} , parseInt(Math.random() * 500) + 200);

	//we have used static data here
	//but you can retrieve your data dynamically from a server using ajax call
	//checkout examples/treeview.html and examples/treeview.js for more info
};

var tree_data = {	
	'account' : {name: 'Account', type: 'folder'}	,
	'services' : {name: 'Services', type: 'folder'}	,
	'character' : {name: 'Character', type: 'item'}	,
	'company' : {name: 'Company', type: 'item'}	,
	'invoice' : {name: 'Invoice', type: 'item'}	,
	'manage-permission' : {name: 'Manage Permission', type: 'item'}
	
	
}

tree_data['account']['additionalParameters'] = {
	'children' : {
		'add-new-account' : {name: 'Add New Account', type: 'item'},
		'edit-account' : {name: 'Edit Account', type: 'item'},
		'delete-account' : {name: 'Delete Account', type: 'item'},
		'view-user-account' : {name: 'View User Account', type: 'item'},
		'view-staff-account' : {name: 'View Staff Account', type: 'item'},
		'change-status' : {name: 'Change Status', type: 'item'}		
	}
}

tree_data['services']['additionalParameters'] = {
	'children' : {
		'primary-services' : {name: 'Primary Services', type: 'item'},
		'secondary-services' : {name: 'Secondary Services', type: 'folder'}		
	}
}
tree_data['services']['additionalParameters']['children']['secondary-services']['additionalParameters'] = {
	'children' : {
		'add-new-service' : {name: 'Add New Service', type: 'item'},
		'delete-services' : {name: 'Delete Services', type: 'item'},
		'edit-services' : {name: 'Edit Services', type: 'item'}
	}
}




var treeDataSource = new DataSourceTree({data: tree_data});










