$(document).ready(function() {
	
	if ($("#is_game_over").val() !== "true" ) {
		$(".board-row-square.available").click(function(event) {
			$("#square_id").val(event.target.id);
			$("#form_mark_square").submit();
		} );
	}
	
	$("#btn-restart").click(function(event) {
		$("#restart").val("yes");
		$("#form_mark_square").submit();
	} );
	
} );