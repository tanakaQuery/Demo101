/**
 *
 */

$("img").hover(
	function() {
		$(this).css("transform", "scale(1.8)");
	},
	function() {
		$(this).css("transform", "scale(1)");
	}
)

/*
let stat = false;

function func1(id) {
	let imageId = "image" + id
	let image = document.getElementById(imageId);
	if (stat) {
		image.classList.add('off');
		image.classList.remove('on');
	} else {
		image.classList.add('on');
		image.classList.remove('off');
	}
	stat = !stat;
}
*/
