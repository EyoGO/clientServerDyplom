<%@ page contentType="text/html; charset=UTF-8" %>

<html>
	<head>
		<title>Розпізнавання страв</title>
		<link rel="stylesheet" href="recognize.css">
	</head>
	
	<body>
	<div id="format" align="center">Розпізнавання страв</div>
	<div id="space">Покладіть страву у місце для розпізнавання та натисніть кнопку</div>
    <div id="container">
		<div id="menu">
			<a href="">
			    <button>Розпочати сканування</button>
		    </a>
		</div>
		<div id="content"></div>
		<div id="addSection">
			<div id="block1">
				Розпізнані страви
			</div>
        
			<div id="block2">
				<table style="width:100%">
					<tr style="background-color: #c2c2c2">
						<th style="width:65%">Страва</th>
						<th>Кількість</th>
					</tr>
					<tr>
					  <td>Страва 1</td>
					  <td>1</td>
					</tr>
					<tr>
					  <td>Страва 2</td>
					  <td>3</td>
					</tr>
				</table>
			</div>
        
			<div id="block3">
			    <a href="submit">
					<button>Завершити сканування</button>
			    </a>
			</div>
        
		</div>
	</div>
    

	</body>
</html>