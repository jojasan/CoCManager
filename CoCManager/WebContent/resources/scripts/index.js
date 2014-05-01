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
	var selectedOp = $("#clanId select.period-select option:selected").val();
	var lastOp = $("#clanId select.period-select option:last").val();
	if(selectedOp == 0) {
		$("#clanId table th input:first").addClass("disabled");
		$("#clanId table th input:last").removeClass("disabled");
	} else if(selectedOp == lastOp) {
		$("#clanId table th input:last").addClass("disabled");
		$("#clanId table th input:first").removeClass("disabled");
	} else {
		$("#clanId table th input").removeClass("disabled");
	}
	console.log("Buttons toggled");
};

$( document ).ready(function() {
	togglePeriodSelectButtons();
});