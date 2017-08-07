//Load Data in Table when documents is ready
$(document).ready(function() {
	$('[data-toggle="tooltip"]').tooltip();
	loadData();
	var counter = 1;
	$("#addrow").on("click", function() {
		var newRow = $("<tr>");
		var cols = "";

		cols += '<td><input type="text" class="form-control" autocomplete="off" placeholder="Key" name="key' + counter + '" id="key' + counter + '"/></td>';
		cols += '<td><input type="text" class="form-control" autocomplete="off" placeholder="Value" name="value' + counter + '" id="value' + counter + '"/></td>';
		cols += '<td><input type="button" class="ibtnDel btn btn-md btn-danger"  value="Delete"></td>';
		newRow.append(cols);
		$("table.order-list").append(newRow);
		counter++;
	});

	$("table.order-list").on("click", ".ibtnDel", function(event) {
		$(this).closest("tr").remove();
		counter -= 1;
	});

});

// Load Data function
function loadData() {
	$.ajax({
		url : "/demo/list",
		type : "GET",
		contentType : "application/json;charset=utf-8",
		// dataType: "json",
		success : function(result) {
			var html = '';
			$.each(result, function(key, item) {
				var flowname = item.flowName;
				html += '<tr>';
				html += '<td>' + flowname + '</td>';
				html += '<td> <button type="button" class="btn btn-info" id="executeBtn" onclick="execute()" value="' + flowname + '">Execute <i class="fa fa-bolt"></i></button>  <button type="button" class="btn btn-danger" id="deleteBtn" onclick="Delele()" value="' + flowname + '" >Delete <i class="fa fa-trash-o"></i></button>    <button type="submit" id="editBtn" class="btn btn-success" onclick="getbyID()" value="' + flowname + '"> Edit <i class="fa fa-pencil-square-o"></i></button> </td>';
				html += '</tr>';
			});
			$('.tbody').html(html);
		},
		error : function(errormessage) {
			alert(errormessage.responseText);
		}
	});
}

function execute() {
	var flowName = $('#executeBtn').attr('value');

	$.ajax({
		url : "/demo/execute/" + flowName,
		type : "GET",
		contentType : "application/json;charset=UTF-8",
		// dataType: "json",
		success : function(result) {
			alert(result);
		},
		error : function(errormessage) {
			alert(errormessage.responseText);
		}
	});

}

// Add Data Function
function Add() {
	var res = validate();
	if (res == false) {
		return false;
	}

	var businessFlowInputMap = [];
	for (var i = 0; i < $('#myTable tr').length - 1; i++) {
		var map = {};
		map['key'] = $('#key' + i).val();
		map['value'] = $('#value' + i).val();

		if (map['key'] != "" && map['value'] != "" && map['key'] != null && map['value'] != null) {
			businessFlowInputMap.push(map);
		}
	}

	var ejbCallInput = {
		flowName : $('#operationName').val(),
		businessFlowInputMap : businessFlowInputMap
	};

	$.ajax({
		url : "/demo/add",
		data : JSON.stringify(ejbCallInput),
		type : "POST",
		contentType : "application/json;charset=utf-8",
		// dataType: "json",
		success : function(result) {
			loadData();
			$('#myModal').modal('hide');
		},
		error : function(errormessage) {
			alert(errormessage.responseText);
		}
	});
}

// Function for getting the Data Based upon Employee ID
function getbyID(flowName) {
	$('#operationName').css('border-color', 'lightgrey');

	$('#operationName').attr("disabled", true);
	$('#myModalLabel').text('Update Operation');
	var flowName = $('#editBtn').attr('value');

	$.ajax({
		url : "/demo/getbyId/" + flowName,
		type : "GET",
		contentType : "application/json;charset=UTF-8",
		// dataType: "json",
		success : function(result) {
			$('#operationName').val(result.flowName);

			var lastIndex = $('#myTable tr').length;
			var lastRow = $('#myTable tr')[lastIndex - 1];

			$('#myTable tr').remove();

			var counter = 0;
			result.businessFlowInputMap.forEach(function(entry) {
				var newRow = $('<tr>');
				var cols = "";
				cols += '<td><input type="text" class="form-control" autocomplete="off" value="' + entry.key + '" placeholder="Key" name="key' + counter + '" id="key' + counter + '"/></td>';
				cols += '<td><input type="text" class="form-control" autocomplete="off" value="' + entry.value + '" placeholder="Value" name="value' + counter + '" id="value' + counter + '"/></td>';
				cols += '<td><input type="button" class="ibtnDel btn btn-md btn-danger" value="Delete"></td>';
				newRow.append(cols);
				$("table.order-list").append(newRow);
				$("table.order-list").append(lastRow);
				counter++;
			});

			$('#myModal').modal('show');
			$('#btnUpdate').show();
			$('#btnAdd').hide();
		},
		error : function(errormessage) {
			alert(errormessage.responseText);
		}
	});
	return false;
}
// function for updating employee's record
function Update() {
	var res = validate();
	if (res == false) {
		return false;
	}

	var businessFlowInputMap = [];
	for (var i = 0; i < $('#myTable tr').length; i++) {
		var map = {};
		map['key'] = $('#key' + i).val();
		map['value'] = $('#value' + i).val();

		if (map['key'] != "" && map['value'] != "" && map['key'] != null && map['value'] != null) {
			businessFlowInputMap.push(map);
		}
	}

	$('#operationName').attr("disabled", true);

	var ejbCallInput = {
		flowName : $('#operationName').val(),
		businessFlowInputMap : businessFlowInputMap
	};

	$.ajax({
		url : "/demo/update",
		data : JSON.stringify(ejbCallInput),
		type : "POST",
		contentType : "application/json;charset=utf-8",
		// dataType: "json",
		success : function(result) {
			loadData();
			$('#myModal').modal('hide');
			$('#operationName').val("");
		},
		error : function(errormessage) {
			alert(errormessage.responseText);
		}
	});
}
// function for deleting employee's record
function Delele(flowName) {
	var ans = confirm("Are you sure you want to delete this Record?");
	if (ans) {

		var flowName = $('#deleteBtn').attr('value');

		$.ajax({
			url : "/demo/delete/" + flowName,
			type : "GET",
			contentType : "application/json;charset=UTF-8",
			// dataType: "json",
			success : function(result) {
				loadData();
			},
			error : function(errormessage) {
				alert(errormessage.responseText);
			}
		});
	}
}

// Function for clearing the textboxes
function clearTextBox() {
	$('#myModalLabel').text('Add Operation');
	$('#operationName').val("");
	$('#btnUpdate').hide();
	$('#btnAdd').show();
	$('#operationName').attr("disabled", false);
	$('#operationName').css('border-color', 'lightgrey');
}
// Valdidation using jquery
function validate() {
	var isValid = true;
	if ($('#operationName').val().trim() == "") {
		$('#operationName').css('border-color', 'Red');
		isValid = false;
	} else {
		$('#operationName').css('border-color', 'lightgrey');
	}

	for (var i = 0; i < $('#myTable tr').length - 1; i++) {
		if ($('#key' + i).val() == "") {
			$('#key' + i).css('border-color', 'Red');
			isValid = false;
		} else {
			$('#key' + i).css('border-color', 'lightgrey');
		}

		if ($('#value' + i).val() == "") {
			$('#value' + i).css('border-color', 'Red');
			isValid = false;
		} else {
			$('#value' + i).css('border-color', 'lightgrey');
		}
	}

	return isValid;
}