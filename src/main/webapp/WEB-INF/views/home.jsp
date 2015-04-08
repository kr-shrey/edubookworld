
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../assets/ico/favicon.ico">

    <title>SEN-HOME</title>

    <!-- Bootstrap core CSS -->
<link rel="stylesheet" href="http://getbootstrap.com/dist/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>


<!-- Latest compiled and minified JavaScript -->
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 

<style>




body.modal-open #wrap{
    -webkit-filter: blur(7px);
    -moz-filter: blur(15px);
    -o-filter: blur(15px);
    -ms-filter: blur(15px);
    filter: blur(15px);
}
  
.modal-backdrop {background: #f7f7f7;}


.timeline {
  list-style: none;
  padding: 20px 0 20px;
  position: relative;
}
.timeline:before {
  top: 0;
  bottom: 0;
  position: absolute;
  content: " ";
  width: 3px;
  background-color: #eeeeee;
  left: 50%;
  margin-left: -1.5px;
}
.timeline > li {
  margin-bottom: 20px;
  position: relative;
  width: 50%;
  float: left;
  clear: left;
}
.timeline > li:before,
.timeline > li:after {
  content: " ";
  display: table;
}
.timeline > li:after {
  clear: both;
}
.timeline > li:before,
.timeline > li:after {
  content: " ";
  display: table;
}
.timeline > li:after {
  clear: both;
}
.timeline > li > .timeline-panel {
  width: 95%;
  float: left;
  border: 1px solid #d4d4d4;
  /*border-radius: 2px;*/
  /*padding: 20px;*/
  position: relative;
  -webkit-box-shadow: 0 1px 6px rgba(0, 0, 0, 0.175);
  box-shadow: 0 1px 6px rgba(0, 0, 0, 0.175);
}

.timeline > li > .timeline-panel:before {
  position: absolute;
  top: 26px;
  right: -15px;
  display: inline-block;
  border-top: 15px solid transparent;
  border-left: 15px solid #ccc;
  border-right: 0 solid #ccc;
  border-bottom: 15px solid transparent;
  content: " ";
}
.timeline > li > .timeline-panel:after {
  position: absolute;
  top: 27px;
  right: -14px;
  display: inline-block;
  border-top: 14px solid transparent;
  border-left: 14px solid #fff;
  border-right: 0 solid #fff;
  border-bottom: 14px solid transparent;
  content: " ";
}
.timeline > li > .timeline-badge {
  color: #fff;
  width: 24px;
  height: 24px;
  line-height: 50px;
  font-size: 1.4em;
  text-align: center;
  position: absolute;
  top: 16px;
  right: -12px;
  /*background-color: #999999;*/
  z-index: 100;
  /*
  border-top-right-radius: 50%;
  border-top-left-radius: 50%;
  border-bottom-right-radius: 50%;
  border-bottom-left-radius: 50%;
  */
}
.timeline > li.timeline-inverted > .timeline-panel {
  float: right;
}
.timeline > li.timeline-inverted > .timeline-panel:before {
  border-left-width: 0;
  border-right-width: 15px;
  left: -15px;
  right: auto;
}
.timeline > li.timeline-inverted > .timeline-panel:after {
  border-left-width: 0;
  border-right-width: 14px;
  left: -14px;
  right: auto;
}
.timeline-badge > a {
  color: #C5C7C5 !important;
}
.timeline-badge a:hover {
  color: #000 !important;
}
.timeline-title {
  margin-top: 0;
  color: inherit;
}
.timeline-body > p,
.timeline-body > ul {
    padding:20px;
    margin-bottom: 0;
}
.timeline-body > p + p {
  margin-top: 5px;
}
.timeline-footer{
    padding:20px;
    background-color:#f4f4f4;
}
.timeline-footer > a{
    cursor: pointer;
    text-decoration: none;
}
.tooltip{

    position:absolute;
    z-index:1020;
    display:block;
    visibility:visible;
    padding:5px;
    font-size:11px;
    opacity:0;
    filter:alpha(opacity=0);
    
}
.tooltip.in{
    /*opacity:0;
    filter:alpha(opacity=80);*/
    
}
.tooltip.top{
    margin-top:-2px;
}
.tooltip.right{
    margin-left:2px;
}
.tooltip.bottom{
    margin-top:2px;
}
.tooltip.left{
    margin-left:-2px;
}
.tooltip.top .tooltip-arrow{
    bottom:0;
    left:0;
    margin-left:0;
    border-left:0 solid transparent;
    border-right:5px solid transparent;
    border-top:0 solid #000;
}
.tooltip.left .tooltip-arrow{
    bottom:0;
    left:0;
    margin-left:0;
    border-left:0 solid transparent;
    border-right:5px solid transparent;
    border-top:0 solid #000;
}
.tooltip.bottom .tooltip-arrow{
    bottom:0;
    left:0;
    margin-left:0;
    border-left:0 solid transparent;
    border-right:5px solid transparent;
    border-top:0 solid #000;
}
.tooltip.right .tooltip-arrow{
    bottom:0;
    left:0;
    margin-left:0;
    border-left:0 solid transparent;
    border-right:5px solid transparent;
    border-top:0 solid #000;
}
.tooltip-inner{
    width:200px;
    padding:3px 8px;
    color:#fff;
    text-align:center;
    text-decoration:none;
    background-color:#313131;
    -webkit-border-radius:0px;
    -moz-border-radius:0px;
    border-radius:0px;
}
.tooltip-arrow{
    position:absolute;
    width:0;
    height:0;
}
.timeline > li.timeline-inverted{
  float: right; 
  clear: right;
  margin-top: 30px;
  margin-bottom: 30px;
}
.timeline > li:nth-child(2){
  margin-top: 60px;
}
.timeline > li.timeline-inverted > .timeline-badge{
  left: -12px;
}

@media (max-width: 767px) {
    ul.timeline:before {
        left: 40px;
    }

    ul.timeline > li {
      margin-bottom: 20px;
      position: relative;
      width:100%;
      float: left;
      clear: left;
    }
    ul.timeline > li > .timeline-panel {
        width: calc(100% - 90px);
        width: -moz-calc(100% - 90px);
        width: -webkit-calc(100% - 90px);
    }

    ul.timeline > li > .timeline-badge {
        left: 28px;
        margin-left: 0;
        top: 16px;
    }

    ul.timeline > li > .timeline-panel {
        float: right;
    }

        ul.timeline > li > .timeline-panel:before {
            border-left-width: 0;
            border-right-width: 15px;
            left: -15px;
            right: auto;
        }

        ul.timeline > li > .timeline-panel:after {
            border-left-width: 0;
            border-right-width: 14px;
            left: -14px;
            right: auto;
        }
    
.timeline > li.timeline-inverted{
  float: left; 
  clear: left;
  margin-top: 30px;
  margin-bottom: 30px;
}

.timeline > li.timeline-inverted > .timeline-badge{
  left: 28px;
}
}


/*
 * Sidebar
 */

/* Hide for mobile, show later :to do */
.sidebar {
  display: none;
}
@media (min-width: 768px) {
  .sidebar {
    position: fixed;
    top: 51px;
    bottom: 0;
    right: 0;
    z-index: 1000;
    display: block;
    padding: 20px;
    overflow-x: hidden;
    overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
    background-color: #f5f5f5;
    border-right: 1px solid #eee;
  }
}

/* Sidebar navigation */
.nav-sidebar {
  margin-right: -21px; /* 20px padding + 1px border */
  margin-bottom: 20px;
  margin-left: -20px;
}
.nav-sidebar > li > a {
  padding-right: 20px;
  padding-left: 20px;
}
.nav-sidebar > .active > a {
  color: #fff;
  background-color: #428bca;
}


      /* Wrapper for page content to push down footer */
      #wrap {
        min-height: 100%;
        height: auto !important;
        height: 100%;
        /* Negative indent footer by it's height */
        margin: 0 auto -60px;
      }

      /* Set the fixed height of the footer here */
      #push,
      #footer {
        height: 60px;
      }
      #footer {
        background-color: #f5f5f5;
     position: fixed;
    height: 30%;
    bottom: 0;
    width: 100%;
   
      }

      /* Lastly, apply responsive CSS fixes as necessary */
      @media (max-width: 767px) {
        #footer {
         
          margin-right: -20px;
          padding-left: 20px;
          padding-right: 20px;
		  
		     position: fixed;
    height: 30%;
    bottom: 0;
    width: 100%;
        }
      }



      /* Custom page CSS
      -------------------------------------------------- */
      /* Not required for template or sticky footer method. */

      #wrap > .container {
        padding-top: 60px;
      }
      .container .credit {
        margin: 20px 0;
      }

      code {
        font-size: 80%;
      }






</style>


    <!-- Custom styles for this template -->

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
  



<!-- Modal -->
<!-- Modal -->
<div class="modal fade bs-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm" style="width:67%">
    <div class="modal-content" style="padding: 15px;">
        <br>
        <div class="bs-example bs-example-tabs">
            <ul id="myTab" class="nav nav-tabs">
              <li class="active"><a href="#upload" data-toggle="tab">Upload</a></li>
              <c:if test="${usrtyp ne 'ROLE_STUDENT'}">
              <li class=""><a href="#Tests" data-toggle="tab">Tests</a></li>
              <li class=""><a href="#Questions" data-toggle="tab">Questions</a></li>
              <li class=""><a href="#CreateCourse" data-toggle="tab">Create Course</a></li>
              <li class=""><a href="#AddTopic" data-toggle="tab">Add Topic to Course</a></li>
              </c:if>
              <c:if test="${usrtyp=='ROLE_STUDENT'}">
              <li class=""><a href="#PersonalTest" data-toggle="tab">Create and take Personal Assessment Tests</a></li></c:if>
            </ul>
        </div>
      <div class="modal-body">
        <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade active in" id="upload">
        
      
								<div id="maincontainer" >
								   
									<div id="form"> 
									<div class="row">
								      <div class="col-md-12">
								        
        <form class="form-horizontal" action="/myapp/UploadFileLearningObject" method="POST" enctype="€multipart/form-data">
<fieldset>

<!-- Form Name -->
<legend>Upload Video/Document file</legend>

<!-- File Button --> 
 <div class="form-group">
								              <div class="col-md-9">
								           

<div class="control-group">
  <label class="control-label" for="filebutton">Choose File</label>
  <div class="controls">
    <input id="filebutton" name="filebutton" class="input-file" type="file">
  </div>
</div>
</div>
</div>

<!-- Text input-->
 <div class="form-group">
								              <div class="col-md-9">
								           

<div class="control-group">
  <label class="control-label" for="fname">Name</label>
  <div class="controls">
    <input id="fname" name="fname" type="text" placeholder="Alternative File Name" class="form-control">
    
  </div>
</div>

</div>
</div>
<!-- Select Basic -->

 <div class="form-group">
								              <div class="col-md-9">
								           

<div class="control-group">
  <label class="control-label" for="courseid">Select Course</label>
  <div class="controls">
    <select id="courseid" name="courseid" class="form-control" >
    <c:if test="${usrcourses.size()==0}"><option>No Subscribed Courses!</option></c:if>
    <option value=0>Select Course</option>
    <c:forEach items="${usrcourses}" var="uscourse">
      <option value="${uscourse.nodeId}">${uscourse.name}</option>
            
      </c:forEach>
    </select>
  </div>
</div>

</div>
</div>
<div class="form-group">
								              <div class="col-md-9">
								           
								<div class="control-group">
  									<label class="control-label" for="topicid">Select Topic</label>
  										<div class="controls" id=topicsd>
   						 					<select id="topicid" name="topicid" class="form-control">
   						 					<option value=0 id=0>Select Topic</option>
      												<c:forEach items="${usrcourses}" var="uscourse">
    <c:forEach items="${uscourse.topics}" var="tpc">
      <option value="${tpc.nodeId}" id="${uscourse.nodeId}">${tpc.name}</option>
      </c:forEach>
      </c:forEach>
        																				</select>
  																			</div>
																</div>

											</div>
</div>

<script>
$(document).ready( function ()
		{
		  /* we are assigning change event handler for select box */

$('#courseid').on('change', function() {
	$("#topicid option[id="+ this.value+"]").show();
	$("#topicid option[id!="+ this.value+"]").hide();
	
  
	  
	});
		  
		});
</script>

<!-- <div class="form-group">
								              <div class="col-md-9">
								           
								<div class="control-group">
  									<label class="control-label" for="topicid">Select Topic</label>
  										<div class="controls">
   						 					<select id="topicid" name="topicid" class="form-control">
      												
    																				</select>
  																			</div>
																</div>

											</div>
</div> -->



<!-- Select Basic

 <div class="form-group">
								              <div class="col-md-9">
								           
<div class="control-group">
  <label class="control-label" for="topicid">Select Topic</label>
  <div class="controls">
    <select id="topicid" name="topicid" class="form-control">
      <c:if test="${slcrtpc.size()==0}"><option>No Topic under the selected Courses!</option></c:if>
        <c:forEach items="${slcrtpc}" var="crtpc">
      <option value="${crtpc.nodeId }">${ crtpc.name }</option>
      </c:forEach>
    </select>
  </div>
</div>

</div>
</div>

) -->

 <div class="form-group">
								              <div class="col-md-9">
								           
<div class="control-group">
  <label class="control-label" for="submit"></label>
  <div class="controls">
    <button id="submit" name="submit" class="btn btn-info">Submit</button>
    <button id="cancel" name="cancel" class="btn btn-danger">Cancel</button>
  </div>
</div>

</div>
</div>

</fieldset>
</form>
        
        </div>
        </div>
        </div>
        </div>
        
        
        
         </div>
         
         
        <div class="tab-pane fade" id="Questions">
            
								            
								            
								<div id="maincontainer" >
								   
									<div id="form"> 
									<div class="row">
								      <div class="col-md-12">
								        <div>
								          <form class="form-horizontal" action="/myapp/createQuestion" method="POST" enctype=â€multipart/form-dataâ€>
								          <fieldset>
								            <legend>Add Question</legend>
								    
								            <!-- Name input-->
								            <div class="form-group">
								              <div class="col-md-9">
								           

<div class="control-group">
  <label class="control-label" for="courseid">Select Course</label>
  <div class="controls">
    <select id="courseid" name="courseid" class="form-control" >
    <c:if test="${usrcourses.size()==0}"><option>No Subscribed Courses!</option></c:if>
    <option value=0>Select Course</option>
    <c:forEach items="${usrcourses}" var="uscourse">
      <option value="${uscourse.nodeId}">${uscourse.name}</option>
            
      </c:forEach>
    </select>
  </div>
</div>

</div>
</div>
<div class="form-group">
								              <div class="col-md-9">
								           
								<div class="control-group">
  									<label class="control-label" for="topicid">Select Topic</label>
  										<div class="controls" id=topicsd>
   						 					<select id="topicid" name="topicid" class="form-control">
   						 					<option value=0 id=0>Select Topic</option>
      												<c:forEach items="${usrcourses}" var="uscourse">
    <c:forEach items="${uscourse.topics}" var="tpc">
      <option value="${tpc.nodeId}" id="${uscourse.nodeId}">${tpc.name}</option>
      </c:forEach>
      </c:forEach>
        																				</select>
  																			</div>
																</div>

											</div>
</div>
<script>
$(document).ready( function ()
		{
		  /* we are assigning change event handler for select box */

$('#courseid').on('change', function() {
	$("#topicid option[id="+ this.value+"]").show();
	$("#topicid option[id!="+this.value+"]").hide();
	
  
	  
	});
		  
		});
</script>
								    
								            <!-- Message body -->
								            <div class="form-group">
								              <label class="col-md-3 control-label" for="message">Question Text</label>
								              <div class="col-md-9">
								                <textarea class="ckeditor" name="editor1" id="message" name="message" placeholder="Please enter your message here..." rows="5"></textarea>
								              </div>
								            </div>
								    
										<div class="form-group">
								              <label class="col-md-3 control-label" for="name">Marks</label>
								              <div class="col-md-3">
								                <input id="marks" name="marks" type="text" class="form-control">
								              </div>
								              </div>
								              <div>
											<label class="control-label" for="filebutton">Choose File related to Question</label>
  											<div class="controls">
    											<input id="filebutton" name="filebutton" class="input-file" type="file">
  											</div>
								        </div>
											
											
								            <!-- Form actions -->
								            <div class="form-group">
								              <div class="col-md-12 text-center">
								                <button type="submit" class="btn btn-primary btn-lg">Submit</button>
								              </div>
								            </div>
								          </fieldset>
								          </form>
								        </div>
								      </div>
									</div>
								</div>
									
								
								</div>
								            
								            
								            
            
                    </div>
                     <div class="tab-pane fade" id="PersonalTest">
                     <div id="maincontainer" >
								   
									<div id="form"> 
									<div class="row">
								      <div class="col-md-12">
								        <div>
            
								            <form class="form-horizontal" action="/myapp/PersonalTest" method="POST" >
<fieldset>

<!-- Form Name -->
<legend>Personalised Test</legend>

<!-- File Button --> 
								            
								            <!-- Name input-->
								            <div class="form-group">
								              <div class="col-md-9">
								           

<div class="control-group">
  <label class="control-label" for="courseid">Select Course</label>
  <div class="controls">
    <select id="courseid" name="courseid" class="form-control" >
    <c:if test="${usrcourses.size()==0}"><option>No Subscribed Courses!</option></c:if>
    <option value=0>Select Course</option>
    <c:forEach items="${usrcourses}" var="uscourse">
      <option value="${uscourse.nodeId}">${uscourse.name}</option>
            
      </c:forEach>
    </select>
  </div>
</div>

</div>
</div>
<div class="form-group">
								              <div class="col-md-9">
								           
								<div class="control-group">
  									<label class="control-label" for="topicid">Select Topic</label>
  										<div class="controls" id=topicsd>
   						 					<select id="topicid" name="topicid" class="form-control">
   						 					<option value=0 id=0>Select Topic</option>
      												<c:forEach items="${usrcourses}" var="uscourse">
    <c:forEach items="${uscourse.topics}" var="tpc">
      <option value="${tpc.nodeId}" id="${uscourse.nodeId}">${tpc.name}</option>
      </c:forEach>
      </c:forEach>
        																				</select>
  																			</div>
																</div>

											</div>
</div>
<script>
$(document).ready( function ()
		{
		  /* we are assigning change event handler for select box */

$('#courseid').on('change', function() {
	$("#topicid option[id="+this.value+"]").show();
	$("#topicid option[id!="+this.value+"]").hide();
	
  
	  
	});
		  
		});
</script>

								            <!-- Form actions -->
								            <div class="form-group">
								              <div class="col-md-12 text-center">
								                <button type="submit" class="btn btn-primary btn-lg">Start</button>
								              </div>
								            </div>
								          </fieldset>
								          </form>
								        </div>
								      </div>
									</div>
								</div>
									
								
								</div>
								            
								            
								            
            
                    </div>
        <div class="tab-pane fade" id="Tests">
							    <div id="maincontainer" >
							   
								<div id="form"> 
								<div class="row">
							      <div class="col-md-12">
							        <div>
							          <form class="form-horizontal" action="/myapp/createTest" method="post">
							          <fieldset>
							            <legend>Add Test</legend>
							    
							            
							    
									
										
										<div class="form-group">
							              <label class="col-md-3 control-label" for="name">Test Name</label>
							              <div class="col-md-3">
							                <input id="marks" name="marks" type="text" class="form-control">
							              </div>
							        </div>
							            			    
								
							    
								
							            <!-- Message body -->
							            <div class="form-group">
							              <label class="col-md-3 control-label" for="message">Test Instruction</label>
							              <div class="col-md-9">
							                <textarea class="ckeditor" name="editor1" id="message" name="message" placeholder="Please enter your message here..." rows="5"></textarea>
							              </div>
							            </div>
							    
								<div class="form-group">
							              <label class="col-md-3 control-label" for="name">Test Time Limit</label>
							              <div class="col-md-3">
							                <input id="marks" name="marks" type="integer" placeholder="Enter time limit in minute" class="form-control">
							              </div>
							
							        </div>
								
									<div class="form-group">
							              <label class="col-md-3 control-label" for="name">Marks</label>
							              <div class="col-md-3">
							                <input id="marks" name="marks" type="text" class="form-control">
							              </div>
							        </div>
										
										<div class="form-group">
							              <label class="col-md-3 control-label" for="name">Date</label>
							              <div class="col-md-3">
							                <input id="marks" name="marks" type="date" class="form-control">
							              </div>
							
							        </div>
									
									
										
										
							            <!-- Form actions -->
							            <div class="form-group">
							              <div class="col-md-12 text-center">
							                <button type="submit" class="btn btn-primary btn-lg">Submit</button>
							              </div>
							            </div>
							          </fieldset>
							          </form>
							        </div>
							      </div>
								</div>
							</div>
								
							
							</div>
    
    
      </div>
      <div class="tab-pane fade" id="CreateCourse">
							    <div id="maincontainer" >
							   
								<div id="form"> 
								<div class="row">
							      <div class="col-md-12">
							        <div>
							          <form modelAttribute="newCourse" class="form-horizontal" action="/myapp/createCourse" method="post">
							          <fieldset>
							            <legend>Create Course</legend>
							    
							            <!-- Name input-->
							            <div class="form-group">
  											<label class="control-label" for="cname">Name</label>
  											<div class="controls">
    											<input path="name" id="courseName" name="courseName" type="text" placeholder="Course Name" class="form-control">
    
  											</div>
										</div>
							    
										<div class="form-group">
  											<label class="control-label" for="stdrd">Class/Standard</label>
  											<div class="controls">
    											<input path="stdrd" id="stdrd" name="stdrd" type="text" placeholder="Class" class="form-control">
    
  											</div>
										</div>
							            <!-- Form actions -->
							            <div class="form-group">
							              <div class="col-md-12 text-center">
							                <button type="submit" class="btn btn-primary btn-lg">Submit</button>
							              </div>
							            </div>
							          </fieldset>
							          </form>
							        </div>
							      </div>
								</div>
							</div>
								
							
							</div>
    
    
      </div>
      <div class="tab-pane fade" id="AddTopic">
							    <div id="maincontainer" >
							   
								<div id="form"> 
								<div class="row">
							      <div class="col-md-12">
							        <div>
							          <form class="form-horizontal" action="/myapp/createTopicUnderCourse" method="post">
							          <fieldset>
							            <legend>Add Topic to Course</legend>
							    
							            <!-- Name input-->
							            <div class="form-group">
							              <label class="col-md-3 control-label" for="name">Course</label>
							              <div class="col-md-9">
							                <select id="courseid" name="courseid" class="form-control"> 
											<c:if test="${usrcourses.size()==0}"><option>No Subscribed Courses!</option></c:if>
        										<c:forEach items="${usrcourses}" var="uscourse">
      												<option value=${uscourse.nodeId}>${uscourse.name }</option>
      											</c:forEach>
							
											</select>
							              </div>
							            </div>
							    
										<div class="form-group">
  											<label class="control-label" for="topicName">Topic Name</label>
  											<div class="controls">
    											<input id="topicName" name="topicName" type="text" placeholder="Topic Name" class="form-control">
    
  											</div>
										</div>
							            <!-- Form actions -->
							            <div class="form-group">
							              <div class="col-md-12 text-center">
							                <button type="submit" class="btn btn-primary btn-lg">Submit</button>
							              </div>
							            </div>
							          </fieldset>
							          </form>
							        </div>
							      </div>
								</div>
							</div>
								
							
							</div>
    
    
      </div>
    </div>
      </div>
      <div class="modal-footer">
      <center>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </center>
      </div>
    </div>
  </div>
</div>
  
  <div id="wrap">
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
                <li><a href="#"><span class=""></span> ${us.getName()}</a></li>
                
            </ul>
			
			
			<div class="col-sm-3 col-md-3">
        <form class="navbar-form" role="search" action="http://bootsnipp.com/search" method="GET" id="search-form">
        <div class="input-group">
            <input type="text" class="form-control" placeholder="Search featured snippets" name="q" style="padding:6px 6px;">
            <div class="input-group-btn">
                <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
            </div>
        </div>
        </form>
    </div>
            <ul class="nav navbar-nav navbar-right">
                
                <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><span
                    class="glyphicon glyphicon-user"></span> ${us.usrName} <b class="caret"></b></a>
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



   
  
  



<div class="container-fluid">


<div class="row">
<div class="col-sm-3 col-md-2 sidebar" style="height: 62%;position:fixed;">
          
		  
		  <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#onlineStudents"><span class="">
                            </span>Online Students</a>
                        </h4>
                    </div>
                    <div id="onlineStudents" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <table class="table">
							<c:if test="${olusers.size()==0}">No Online Users!</c:if>
							<c:forEach items="${olusers}" var="oluser">
                                <tr>
                                    <td>
                                        <a href="/myapp/profile/${oluser.usrName}">${oluser.name}</a>
                                    </td>
                                </tr>
								</c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#onlineMentors"><span class="">
                            </span>Online Mentors</a>
                        </h4>
                    </div>
                    <div id="onlineMentors" class="panel-collapse collapse">
                        <div class="panel-body">
						<c:if test="${olm_user.size()==0}">No Online Users!</c:if>
							<c:forEach items="${olm_user}" var="ment">
                            <table class="table">
                                <tr>
                                    <td>
                                        <a href="/myapp/profile/${olment.usrName}/">${olment.name}</span>
                                    </td>
                                </tr>
								</c:forEach>
                                </table>
                        </div>
                    </div>
                </div>
            </div>
         <hr>
         
        </div> 
		</div>
        


<div class="col-sm-3 col-md-2 sidebar" style="top:inherit;height: 32%;position:fixed;">
         

<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">

Contribute
</button>
<div id="creds"><p>User Credits: ${usrcredits}</p></div>




         </div>

<div class="col-md-10"  style="margin-top: 50px;height: 62%;overflow-x: overlay;position: fixed;">


<div id="maincontainer" >
    <div class="page-header text-center">
        <h1 id="timeline">Notifications</h1>
		
    </div>
    <ul class="timeline">
    <c:if test="${neewsfeed.size()==0}">Welcome User</c:if>
    <c:forEach items="${newsfeed}" var="notify">
    	<li>
          <div class="timeline-badge primary"><a><i class="glyphicon glyphicon-record" rel="tooltip" title="${notify.add}" id=""></i></a></div>
          <div class="timeline-panel">
            <div class="timeline-heading">
              <!--<img class="img-responsive" src="http://lorempixel.com/1600/500/sports/2" />-->
              
            </div>
            <div class="timeline-body">
              <p>${notify.detail}.</p>
              
            </div>
            
            <div class="timeline-footer">
                <a><i class="glyphicon glyphicon-thumbs-up"></i></a>
                <a><i class="glyphicon glyphicon-share"></i></a>
                <a class="pull-right" href="myapp/${notify.leo.course.name}/t/${notify.leo.topic.name}/l/${notify.leo.name}.learningObject">Continue</a>
            </div>
          </div>
        </li>
    </c:forEach>
  
        <li class="clearfix" style="float: none;"></li>
    </ul>
</div>



    
	</div>
	
</div>
 
 </div>
 
 <div id="footer" class="container">
     
	 <div>
<ul class="nav nav-pills nav-stacked col-md-2">
  <li class="active"><a href="#tab_a" data-toggle="pill">Course</a></li>
  <li><a href="#tab_b" data-toggle="pill">Repository</a></li>
  <li><a href="#tab_c" data-toggle="pill">Options</a></li>
</ul>
<div class="tab-content col-md-8" style="
    height: 30%;
    overflow: auto;
">
        <div class="tab-pane active" id="tab_a">
             
				<div>
<ul class="nav nav-pills nav-stacked col-md-2" style="float: left;padding:3px;overflow-y: scroll;display: block;position: relative;height: 185px;">
<c:if test="${usrcourses.size()==0}"><li>No Courses!</li></c:if>
        <c:forEach items="${usrcourses}" var="uscourse">
        <li><a data-toggle="" href="/myapp/${uscourse.name}.course" >${uscourse.name}</a>
        </li>
        	<!-- <div class="tab" id="${uscourse.name}" ></div> -->
        </c:forEach>
</ul>

</div><!-- end of container -->
	 
				
        </div>
        <div class="tab-pane" id="tab_b" >
             <h4>Repository</h4>

<ul class="nav nav-pills nav-stacked col-md-2" style="float: left; height: 163PX;padding:3px;"  >
  <c:if test="${usrrepo.size()==0}"><li>No Learning Object in Reposiotry!</li></c:if>
        <c:forEach items="${usrrepo}" var="repo">
        <li><a href="/myapp/${repo.course.nodeId }/t/${repo.topic.nodeId}/l/${repo.nodeId}.learningObject" data-toggle="pill">${repo.name}</a>
        </li>
        </c:forEach>
</ul>



        </div>
        <div class="tab-pane" id="tab_c">
             <h4>Options</h4>
            <p></p>
        </div>
</div><!-- tab content -->
</div><!-- end of container -->
	 
	 
    </div>
 
 
 
 
    
  </body>
</html>
