//[Horizontal Menu Javascript]

//Project:	Lion Admin - Responsive Admin Template
//Primary use:   Lion Admin - Responsive Admin Template

//should be included in all pages. It controls some layout


+function ($) {
  'use strict'

	jQuery(document).on('click', '.mega-dropdown', function (e) {
	e.stopPropagation();
	});
  
 
	var $window = $(window),
		$html = $('html');

	$window.resize(function resize() {
		if ($window.width() < 990) {
			return $html.removeClass('horizontal-menu');
		}

		$html.addClass('horizontal-menu');
	}).trigger('resize');			
	
}(jQuery)


