$(document).ready(function(){
  $("#startbutton").click(function(){
    $( "#block2" ).load( "recognize #block2", { "test": $("photo").attr("src") }, function() {
            alert( "The last 25 entries in the feed have been loaded" );
          });
  });
});