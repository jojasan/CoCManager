$('#mainMenuBarId a').click(function (e) {
  e.preventDefault();
  $(this).tab('show');
});

//$('#createMemberModal').modal({});

function checkPeriodChange(data) {
	if (data.status == "success") {
		togglePeriodSelectButtons();
	}
}

function togglePeriodSelectButtons() {
	var selectedOp = $("#membersId select.period-select option:selected").val();
	var lastOp = $("#membersId select.period-select option:last").val();
	if(selectedOp == 0) {
		$("#membersId table th input:first").addClass("disabled");
		$("#membersId table th input:last").removeClass("disabled");
	} else if(selectedOp == lastOp) {
		$("#membersId table th input:last").addClass("disabled");
		$("#membersId table th input:first").removeClass("disabled");
	} else {
		$("#membersId table th input").removeClass("disabled");
	}
	console.log("Buttons toggled");
};

$( document ).ready(function() {
	togglePeriodSelectButtons();
});