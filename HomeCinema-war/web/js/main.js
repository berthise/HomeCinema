$(function () {
    $("#login").click(function () {
        if (window.location.protocol != "https:")
	  $("#warning-http").show();
        console.log($("#form-login").length);
        $("#form-login").slideToggle();
    });
    
    $("#dt_button").click(function () {
        $("#dl_popup").show();
    });
    
    $("#warning-http-lien").on("click", function (e) {
	e.preventDefault();
        if (window.location.protocol != "https:")
	  window.location.href = "https:" + 
		window.location.href.substring(window.location.protocol.length);
    });

    var vid = document.getElementById("myVideo");

    var playing = false;

    function saveCurrentTime() {
        if (($("#updateCT").length > 0) && playing) {
            $("#ufCP").val(parseInt(vid.currentTime));
            $("#lastPosition").text(parseInt(vid.currentTime));
            console.log("Updating video current time to the data base");
            $("#updateCT").trigger("click");
        }
    }
    $("#myVideo").on({
        pause: function (e) {
            $("#ufCP").val(parseInt(vid.currentTime));
            $("#lastPosition").text(parseInt(vid.currentTime));
            console.log("Pause : " + parseInt(vid.currentTime));
            $("#updateCT").trigger("click");
            playing = false;
            clearInterval(update);
        },
        play: function (e) {
            console.log("Play : " + vid.currentTime);
            playing = true;
            update = setInterval(saveCurrentTime, 5000);
        }
    });
    if($("#lastPosition").length > 0) {
        vid.currentTime = $("#lastPosition").text();
    }
    $(window).scroll(function () {
        var header = $('#header'), scroll = $(window).scrollTop();

        if (scroll >= 80) {
            header.addClass('fixed');
            $(".userTools").slideUp();
            $("#search-form").prev().show().removeClass("bounceOutRight").addClass("bounceInRight");
            $("#form-login").slideUp();
        } else {
            header.removeClass('fixed');
            $(".userTools").slideDown();
            $("#search-form").prev().removeClass("bounceInRight").addClass("bounceOutRight").slideUp("slow");
        }
        
        if(scroll >= 200) {
            $(".sidebar").addClass("fixed");
        } else {
            $(".sidebar").removeClass("fixed");
        }
    });
});

var myLang;
var myQuality;
var myFormat;

function chargeLang(lang,quality,format) {
  /*if (videos != null )
    console.log("llllllllll"+videos); */
  console.log("charge lang=" + lang + " quality="+quality+ " format="+format);
     document.getElementById('format-'+lang).style.display = '';
     document.getElementById('format-'+lang+'-'+quality).style.display = '';
   myLang = lang;
   myQuality = quality;
   myFormat = format;
}
function selectLang(value) {

  console.log("myLang = " + myLang);
  console.log('format-' + value);

   /* Hide unused form elem */
   document.getElementById('format-'+myLang).style.display = 'none';
   if (myQuality != null)
     document.getElementById('format-'+myLang+'-'+myQuality).style.display = 'none';
   myLang = value;
      
   /* show newly use form elem */
   var qualityNode = document.getElementById('format-'+myLang);
   qualityNode.style.display = '';
   var selectQuality = qualityNode.getElementsByTagName("select");
   console.log(selectQuality[0].value);
   myQuality = selectQuality[0].value;
   
   var formatNode = document.getElementById('format-'+myLang+'-'+myQuality);
   formatNode.style.display = '';
   var selectFormat = formatNode.getElementsByTagName("select");
   myFormat = selectFormat[0].value;
   

}
function selectQuality(value) {

  console.log("myQuality = " + myLang);
  console.log('format-'+myLang+'-' + value);

   if (myQuality != null)
    document.getElementById('format-'+myLang+'-'+ myQuality).style.display = 'none';
   myQuality = value;
   
   var formatNode = document.getElementById('format-'+myLang+'-'+myQuality);
   formatNode.style.display = '';
   var selectFormat = formatNode.getElementsByTagName("select");
   myFormat = selectFormat[0].value;

}

function selectFormat(value) {

  console.log("myFormat = " + myFormat);
   myFormat = value;
}

function valideVideo() {
  console.log('valideVideo');
  var st = 'video-'+myLang+'-'+myQuality+'-'+myFormat;
  var link = document.getElementById(st);
  if (link != null) {
	link.click();
  }
}

function viewVideo(video) {
  console.log('viewVideo');
  var v = document.getElementById(video);
  var st = 'video-'+myLang+'-'+myQuality+'-'+myFormat;
  var link = document.getElementById(st);

  while (v.firstChild) {
	 v.removeChild(v.firstChild);
  }
  var s = document.createElement("source");
  s.setAttribute("src", link.getAttribute("href"));
  v.appendChild(s);
  v.load();
}
var prevState = '0';
function videoPause() {
  var v = document.getElementById("myVideo");
  if ( ! v.paused ) {
    v.pause();
    prevState = 'played';
  }
}

function videoPlay() {
  var v = document.getElementById("myVideo");
  if ( prevState == 'played' ) {
    v.play();
  }
}

