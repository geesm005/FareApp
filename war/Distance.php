<?php
	header('Context-Type: text/javascript');
	header('Cache-Control: no-cache');
	header('Pragma: no-cache');
	
$url = $_GET["url"];
$content = file_get_contents($url);
echo $content;

?>