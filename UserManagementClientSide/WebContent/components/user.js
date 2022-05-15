$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == ""){
		$("#alertSuccess").hide();
	}
	
	$("#alertError").hide();
});
	
//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
		
	// Form validation-------------------
	var status = validateUserForm();
	if (status != true){
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
		
	
});



function onUserSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		} else if (status == "error")
		{
			$("#alertError").text("Error while saving.");
			$("#alertError").show();
		} else
		{
			$("#alertError").text("Unknown error while saving..");
			$("#alertError").show();
	}
		$("#hidConIDSave").val("");
		$("#formCon")[0].reset();
}




$(document).on("click", ".btnUpdate", function(event)
{
	
	$("#userId").val($(this).closest("tr").find('td:eq(1)').text());
	$("#name").val($(this).closest("tr").find('td:eq(2)').text());
	$("#dob").val($(this).closest("tr").find('td:eq(3)').text());
	$("#nicNo").val($(this).closest("tr").find('td:eq(4)').text());
	$("#phoneNo").val($(this).closest("tr").find('td:eq(5)').text());
	$("#email").val($(this).closest("tr").find('td:eq(6)').text());
	$("#address").val($(this).closest("tr").find('td:eq(7)').text());
	$("#password").val($(this).closest("tr").find('td:eq(8)').text());
});



$(document).on("click", ".btnRemove", function(event)
		{
			$.ajax(
			{
				url : "userAPI",
				type : "DELETE",
				data : "userId=" + $(this).data("userId"),
				dataType : "text",
				complete : function(response, status)
				{
					onUserDeleteComplete(response.responseText, status);
				}
			});
});


function onUserDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		} else if (status == "error")
		{
			$("#alertError").text("Error while deleting.");
			$("#alertError").show();
		} else
		{
			$("#alertError").text("Unknown error while deleting..");
			$("#alertError").show();
	}
}



function validateConsumptionForm()
{
		
		if ($("#name").val().trim() == "")
		{
			return "Insert Name!!";
		}
		
		if ($("#dob").val().trim() == "")
		{
			return "Insert date of birth!!";
		}
		
		if ($("#nicNo").val().trim() == "")
		{
			return "Insert Nic number!!";
		}
		
		if ($("#phoneNo").val().trim() == "")
		{
			return "Insert phone number!!";
		}
		
		if ($("#email").val().trim() == "")
		{
			return "Insert email!!";
		}
		
		
		if ($("#address").val().trim() == "")
		{
			return "Insert address!!";
		}
		
		
		
		// is numerical value
		var nicNo = $("#nicNo").val().trim();
		if (!$.isNumeric(nicNo))
		{
			return "Invalid nicNo (Please enter a number)";
		}
		
		var phoneNo = $("#phoneNo").val().trim();
		if (!$.isNumeric(phoneNo))
		{
			return "Invalid phone number (Please enter a number)";
		}
		
		return true;
}
