var productCard;
var lastChar;
function viewDetails(elementID){
		var btnID = elementID.id;
		
		productCard = elementID.offsetParent.parentElement.id;
		lastChar = String(productCard[productCard.length -1]);
		/*alert(''+ lastChar +'');*/
		
		/*		$('div.carouselNext, div.carouselPrev').removeClass('visible');*/
		$('#carousel-next-'+lastChar+', #carousel-prev-'+lastChar+'').removeClass('visible');
		$('#product-card-'+lastChar+'').addClass('flip-10');
		setTimeout(function(){
			$('#product-card-'+lastChar+'').removeClass('flip-10').addClass('flip90').find('div.shadow').show().fadeTo( 80 , 1, function(){
				$('#product-front-'+lastChar+', #product-front-'+lastChar+' div.shadow').hide();			
			});
		}, 50);
		
		setTimeout(function(){
			$('#product-card-'+lastChar+'').removeClass('flip90').addClass('flip190');
			$('#product-back-'+lastChar+'').show().find('div.shadow').show().fadeTo( 90 , 0);
			setTimeout(function(){				
				$('#product-card-'+lastChar+'').removeClass('flip190').addClass('flip180').find('div.shadow').hide();						
				setTimeout(function(){
					$('#product-card-'+lastChar+'').css('transition', '100ms ease-out');			
					$('#cx-'+lastChar+', #cy-'+lastChar+'').addClass('s1');
					setTimeout(function(){$('#cx-'+lastChar+', #cy-'+lastChar+'').addClass('s2');}, 100);
					setTimeout(function(){$('#cx-'+lastChar+', #cy-'+lastChar+'').addClass('s3');}, 200);				
					$('#carousel-next-'+lastChar+', #carousel-prev-'+lastChar+'').addClass('visible');				
				}, 100);
			}, 100);			
		}, 150);
		
		
			
		/* ----  Image Gallery Carousel   ---- */
		
		var carousel = $('#div-carousel-'+lastChar+' ul');
		var carouselSlideWidth = 335;
		var carouselWidth = 0;	
		var isAnimating = false;
		
		// building the width of the casousel
		$('#div-carousel-'+lastChar+' li').each(function(){
			carouselWidth += carouselSlideWidth;
		});
		$(carousel).css('width', carouselWidth);
		
		// Load Next Image
		$('#carousel-next-'+lastChar+'').on('click', function(){
			var currentLeft = Math.abs(parseInt($(carousel).css("left")));
			var newLeft = currentLeft + carouselSlideWidth;
			if(newLeft == carouselWidth || isAnimating === true){return;}
			$('#div-carousel-'+lastChar+' ul').css({'left': "-" + newLeft + "px",
								   "transition": "300ms ease-out"
								 });
			isAnimating = true;
			setTimeout(function(){isAnimating = false;}, 300);			
		});
		
		// Load Previous Image
		$('#carousel-prev-'+lastChar+'').on('click', function(){
			var currentLeft = Math.abs(parseInt($(carousel).css("left")));
			var newLeft = currentLeft - carouselSlideWidth;
			if(newLeft < 0  || isAnimating === true){return;}
			$('#div-carousel-'+lastChar+' ul').css({'left': "-" + newLeft + "px",
								   "transition": "300ms ease-out"
								 });
		    isAnimating = true;
			setTimeout(function(){isAnimating = false;}, 300);			
		});
				
} 

function flipClose(elementID){
	var productCardClose = elementID.offsetParent.parentElement.id;
	var lastCharClose = String(productCardClose[productCardClose.length -1]);
	
	$('#product-card-'+lastCharClose+'').removeClass('flip180').addClass('flip190');
	setTimeout(function(){
		$('#product-card-'+lastCharClose+'').removeClass('flip190').addClass('flip90');
	
		$('#product-back-'+lastCharClose+' div.shadow').css('opacity', 0).fadeTo( 100 , 1, function(){
			$('#product-back-'+lastCharClose+', #product-back-'+lastCharClose+' div.shadow').hide();
			$('#product-front-'+lastCharClose+',#product-front-'+lastCharClose+' div.shadow').show();
		});
		
	}, 50);

	setTimeout(function(){
		$('#product-card-'+lastCharClose+'').removeClass('flip90').addClass('flip-10');
		$('#product-front-'+lastCharClose+' div.shadow').show().fadeTo( 100 , 0);
		setTimeout(function(){						
			$('#product-front-'+lastCharClose+' div.shadow').hide();
			$('#product-card-'+lastCharClose+'').removeClass('flip-10').css('transition', '100ms ease-out');		
			$('#cx-'+lastCharClose+', #cy-'+lastCharClose+'').removeClass('s1 s2 s3');			
		}, 100);			
	}, 150);
}

