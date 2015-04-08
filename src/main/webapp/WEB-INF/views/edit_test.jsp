<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title> TEST NAME</title>



<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.4/jquery-ui.min.js"></script>


<link rel="stylesheet" href="http://getbootstrap.com/dist/css/bootstrap.min.css">


<!-- Latest compiled and minified JavaScript -->
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>




		<script src="http://studygujarat.com/assets/superfish/js/hoverIntent.js"></script>
		<script src="http://studygujarat.com/assets/superfish/js/superfish.js"></script>
		<script src="http://studygujarat.com/assets/superfish/js/supersubs.js"></script>

	  <link href="http://studygujarat.com/assets/superfish/css/superfish.css" rel="stylesheet" media="screen">


		
		<link href="http://studygujarat.com/assets2/teststyle/teststyle.css" type="text/css" media="screen" rel="stylesheet">

	
	
<script type="text/javascript"> 
$(document).ready(function() { 

  $("ul.sf-menu").supersubs({ 
            minWidth:    12,   // minimum width of sub-menus in em units 
            maxWidth:    27,   // maximum width of sub-menus in em units 
            extraWidth:  1     // extra width can ensure lines don't sometimes turn over 
                               // due to slight rounding differences and font-family 
        }).superfish();  // call supersubs first, then superfish, so that subs are 
                         // not display:none when measuring. Call before initialising 
                         // containing tabs for same reason. 

	// THANKS FOR YOUR HELP :) - Whit

	$('.pagetab').click(function() {
		show_page(extract_id($(this).attr('id')));
	});

	
	$(".column").sortable({
		connectWith: '.questions',
		opacity: 0.6,
		tolerance: 'pointer',
		placeholder: 'place_holder',
		helper: function(event, el) {
			var myclone = el.clone();
			$('body').append(myclone);
			return myclone;
		},
		

		
	}).disableSelection();
	
	$(".column2").sortable({
		connectWith: '.questions',
		opacity: 0.6,
		tolerance: 'pointer',
		placeholder: 'place_holder',
		helper: function(event, el) {
			var myclone = el.clone();
			$('body').append(myclone);
			return myclone;
		},
		update: function( event, ui ) {
				
				var basketItems = $(this).sortable('toArray');
			
				document.getElementById('hidden').value = JSON.stringify(basketItems);
				
				

			}

		
	}).disableSelection();


	
	

	$(".pagetab").droppable({
		over: function(event, ui) {
			$(".pagetab").removeClass('pagetab_drop');
			$(this).addClass('pagetab_drop')
			show_page(extract_id($(this).attr('id')));
		},
		out: function(event, ui) {
			$(".pagetab").removeClass('pagetab_drop');
		},
		tolerance: 'pointer'
	});

});

function show_page(id) {
	$('.page').removeClass('page_active');
	$('#page_'+id).addClass('page_active');
}

function extract_id(str) {
	return str.substr(str.indexOf('_')+1,str.length+1);
}


</script>


<style type="text/css">

.well {
background:white;!important
border: 1px solid rgb(227, 22, 227);
margin: 12px;
box-shadow: 0px 0px 17px 0px rgba(14, 14, 22, 0.7);


}

.pull-right .dropdown-menu:after {
    left: auto;
    right: 13px;
}
.pull-right .dropdown-menu {
    left: auto;
    right: 0;
}
.navbar .nav > li {

  padding-left: 10px;

}

.scrollspy-question  > li {

display: list-item; 
list-style-type: decimal;
}


.scrollspy-question {
height: 500px;
overflow: auto;
width:40%;
position: relative;
float:left;
}




</style>


		
		
		

		
	</head>

<body>

 

<form id="myForm" action= "#" method="post"> 
<input name="hidden[]" id="hidden" type="hidden" />

<div class='pagetabs'>
		<div class='pagetab' id='tab_1'></div>
		<div class='pagetab' id='tab_2'></div>
	</div>
<div class="main-body page page_active" id='page_1'>
	  

		
	    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">EduBook World</a>
        </div>
        <div class="navbar-collapse collapse" style="height: 1px;">
		
		<ul class="nav navbar-nav">
                <li><a href="#"><span class="glyphicon glyphicon-calendar"></span> Bob Builder</a></li>
                
            </ul>
			
			
			<div class="col-sm-3 col-md-3">
        
    </div>
            <ul class="nav navbar-nav navbar-right">
                
                <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><span
                    class="glyphicon glyphicon-user"></span> bob_teacher <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="/myapp/self"><span class="glyphicon glyphicon-user"></span>Profile</a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-cog"></span>Settings</a></li>
                        <li class="divider"></li>
                        <li><a href="/myapp/logout"><span class="glyphicon glyphicon-off"></span>Logout</a></li>
                    </ul>
                </li>
            </ul>
		
        </div>
      </div>
    </div>



		
					
					
		
	</div>

	</div>
	
	
			  </div>
			</div>
		</div>

<script>
    $(document).ready(function () {
        $('label.tree-toggler').click(function () {
            $(this).parent().children('ul.tree').toggle(300);
        });
    });
</script>
	
	
	<div class="row" style="margin-top:4%;">
	
	<div class="row" style="margin-left:30px; margin-right:30px; ">
	<h3> Edit Test <input type="submit" class="btn btn-success pull-right" name="q" > </h3>

	</div>
		<hr>
	<div class="col-md-2">
		
		  
		<div>
		    <div style="overflow-y: scroll; overflow-x: hidden; height: 629px;">
		        <ul class="nav nav-list">
		        	
		            <li><label class="tree-toggler nav-header">Subject 1</label>
		                
		                <ul class="nav nav-list tree">
		                		
		                
		                <li><a href="#">topicname 1</a></li>
		                <li><a href="#">topicname 2</a></li>
		                <li><a href="#">topicname 3</a></li>
		                <li><a href="#">topicname 4</a></li>
			
		                   
		                   
		                  </ul> 
		                    </li>
		                    		            <li class="divider"></li>
		                    
							
							 <li><label class="tree-toggler nav-header">Subject 2</label>
		                
		                <ul class="nav nav-list tree">
		                		
		                
		                <li><a href="#">topicname 1</a></li>
		                <li><a href="#">topicname 2</a></li>
		                <li><a href="#">topicname 3</a></li>
		                <li><a href="#">topicname 4</a></li>
			
		                   
		                   
		                  </ul> 
		                    </li>
								            <li class="divider"></li>
		                    
							
							 <li><label class="tree-toggler nav-header">Subject 3</label>
		                
		                <ul class="nav nav-list tree">
		                		
		                
		                <li><a href="#">topicname 1</a></li>
		                <li><a href="#">topicname 2</a></li>
		                <li><a href="#">topicname 3</a></li>
		                <li><a href="#">topicname 4</a></li>
			
		                   
		                   
		                  </ul> 
		                    </li>
							
								            <li class="divider"></li>
		                    
							 <li><label class="tree-toggler nav-header">Subject 4</label>
		                
		                <ul class="nav nav-list tree">
		                		
		                
		                <li><a href="#">topicname 1</a></li>
		                <li><a href="#">topicname 2</a></li>
		                <li><a href="#">topicname 3</a></li>
		                <li><a href="#">topicname 4</a></li>
			
		                   
		                   
		                  </ul> 
		                    </li>
								            <li class="divider"></li>
		                    
							
							 <li><label class="tree-toggler nav-header">Subject 5</label>
		                
		                <ul class="nav nav-list tree">
		                		
		                
		                <li><a href="#">topicname 1</a></li>
		                <li><a href="#">topicname 2</a></li>
		                <li><a href="#">topicname 3</a></li>
		                <li><a href="#">topicname 4</a></li>
			
		                   
		                   
		                  </ul> 
		                    </li>
							
							
							
							
							
		                </ul>
		         
		        
		    </div>
		</div>
		

</div>






        
		<div data-spy="scroll" data-offset="50" class="scrollspy-question column2 questions col-md-4" style="background: rgb(189, 210, 255); height:572px;"> 
		
				
			 <li  class="well" id="1">
			<h3 > topic name </h3>
            <p>
            a long question text to fuzz your mind.
				
			</p>
			
	
			<p> Marks: only 1 </p>
			<p> Correct answer: a </p>

			</p>
			
						
			
			</li>
	



				
			 <li  class="well" id="2">
			<h3 > topic name </h3>
            <p>
            a long question text to fuzz your mind.
				
			</p>
			
	
			<p> Marks: only 1 </p>
			<p> Correct answer: a </p>

			</p>
			
						
			
			</li>
	
	
	
	
					
			 <li  class="well" id="3">
			<h3 > topic name </h3>
            <p>
            a long question text to fuzz your mind.
				
			</p>
			
	
			<p> Marks: only 1 </p>
			<p> Correct answer: a </p>

			</p>
			
						
			
			</li>

			
							
			 <li  class="well" id="4">
			<h3 > topic name </h3>
            <p>
            a long question text to fuzz your mind.
				
			</p>
			
	
			<p> Marks: only 1 </p>
			<p> Correct answer: a </p>

			</p>
			
						
			
			</li>

           
		</div>
		
	
	

		<div data-spy="scroll" data-target="#navbarExample" data-offset="50" class="scrollspy-question column questions col-md-4" style="height:572px;">
						
			
			 
					 <li class="well draggable" id="11">
					<h3 >  Topic name </h3>
					<p>
					
				   a long question text
				</p>
					
			
					<p> Marks: 1 </p>
					
					<p>Corrrect Ans: 1 </p>
					
								
					
					</li>
					 <li class="well draggable" id="12">
					<h3 >  Topic name </h3>
					<p>
					
				   a long question text
				</p>
					
			
					<p> Marks: 1 </p>
					
					<p>Corrrect Ans: 1 </p>
					
								
					
					</li>

					 <li class="well draggable" id="13">
					<h3 >  Topic name </h3>
					<p>
					
				   a long question text
				</p>
					
			
					<p> Marks: 1 </p>
					
					<p>Corrrect Ans: 1 </p>
					
								
					
					</li>

					 <li class="well draggable" id="14">
					<h3 >  Topic name </h3>
					<p>
					
				   a long question text
				</p>
					
			
					<p> Marks: 1 </p>
					
					<p>Corrrect Ans: 1 </p>
					
								
					
					</li>

					 <li class="well draggable" id="15">
					<h3 >  Topic name </h3>
					<p>
					
				   a long question text
				</p>
					
			
					<p> Marks: 1 </p>
					
					<p>Corrrect Ans: 1 </p>
					
								
					
					</li>
					
						
           
		</div>
		
		</div>
			
		</div>
		

				
		
		
		
		</form>

		
					
					
							
			
		


 
 
   
</body></html>