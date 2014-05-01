$('#mainMenuBarId a').click(function (e) {
  e.preventDefault();
  $(this).tab('show');
});

//$('#createMemberModal').modal({});

function checkClanUpdateResult(data) {
	if (data.status == "success") {
		togglePeriodSelectButtons();
	}
}

function togglePeriodSelectButtons() {
	var selectedOp = $("#clanId div.period-select option:selected").val();
	var lastOp = $("#clanId div.period-select option:last").val();
	if(selectedOp == 0) {
		$("#clanId div.period-select input:first").addClass("disabled");
		$("#clanId div.period-select input:last").removeClass("disabled");
	} else if(selectedOp == lastOp) {
		$("#clanId div.period-select input:last").addClass("disabled");
		$("#clanId div.period-select input:first").removeClass("disabled");
	} else {
		$("#clanId div.period-select input").removeClass("disabled");
	}
	console.log("Buttons toggled");
};

$( document ).ready(function() {
	togglePeriodSelectButtons();
});