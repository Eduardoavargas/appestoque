<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<!-- <link type="text/css" rel="stylesheet" href="/css/padrao.css" /> -->

<style type="text/css">


 body{  
 	background:#f8f8f8;
 	font:13px Trebuchet MS, Arial, Helvetica, Sans-Serif;
 	color:#333;
 	line-height:160%; 
 	margin:0; 
 	padding:0; 
/*  	text-align:center; */ */
 	}


/* form 2 */

	#form2{
		margin:1em 0;
		color:#fff;
		width:320px; /* customize width, this form have fluid layout */
		}
	#form2 h3{
		margin:0;
		background:#57a700 url(/img/form_heading.gif) repeat-x;		
		color:#fff;
		font-size:20px;
		border:1px solid #57a700;
		border-bottom:none;
		}		
	#form2 h3 span{
		display:block;
		padding:10px 20px;
		background:url(/img/form_ico.gif) no-repeat 93% 50%;			
		}				
	#form2 fieldset{
		margin:0;
		padding:0;
		border:none;	
		border-top:3px solid #000;
		background:#000 url(/img/form_top.gif) repeat-x;		
		padding-bottom:1em;
		}		
	#form2 legend{display:none;}	
	#form2 p{margin:.5em 20px;}	
	#form2 label{display:block;}	
	#form2 input, #form2 textarea{		
		width:272px;
		border:1px solid #111;
		background:#282828 url(/img/form_input.gif) repeat-x;
		padding:5px 3px;
		color:#fff;
		}		
	#form2 textarea{
		height:125px;
		overflow:auto;
		}					
	#form2 p.submit{
		text-align:right;
		}	
	#form2 button{
		padding:0 20px;
		height:32px;
		line-height:32px;		
		border:1px solid #70ad2e;
		background:#5aae00 url(/img/form_button.gif) repeat-x;
		color:#fff;
		cursor:pointer;		
		text-align:center;		
		}				

h1{
	font-size:200%;
	font-weight:normal;
	}		
h2, h3, h4, h5, h6{
	font-weight:normal;
	margin:1em 0;
	}	
h2{            
	font-size:160%;
	}	
h3{          
	font-size:140%;
	}
h4{          
	font-size:120%;
	}				

a{
	text-decoration:none;
	color:#f30;
	}
a:hover{
	color:#999;
	}			
table, input, textarea, select, li{
	font:100% Trebuchet MS, Arial, Helvetica, Sans-Serif;
	line-height:160%;
	color:#333;
	}				
p, blockquote, ul, ol, form{
	margin:1em 0;
	}
blockquote{
	}
img{
	border:none;
	}			
hr{
	display:none;
	}	
table{
	margin:1em 0;
	width:100%;
	border-collapse:collapse;
	}
th, td{	
	padding:2px 5px;
	}	
th{	
	text-align:left;
	}
li{
	display:list-item;
	}	
		

</style>

</head>
<body>

	<div id="container">
	<form id="form2" action="/" method="post">	
		
			<h3><span>Contact Us</span></h3>
		
			<fieldset><legend>Contact form</legend>
				<p class="first">
					<label for="name">Name</label>
					<input type="text" name="name" id="name" size="30" />
				</p>
				<p>
					<label for="email">Email</label>
					<input type="text" name="email" id="email" size="30" />
				</p>
				<p>
					<label for="web">Website</label>
					<input type="text" name="web" id="web" size="30" />
				</p>																					
				<p>
					<label for="message">Message</label>
					<textarea name="message" id="message" cols="30" rows="10"></textarea>
				</p>					
				
				<p class="submit"><button type="submit">Send</button></p>		
							
			</fieldset>					
						
		</form>	
	</div>

</body>
</html>