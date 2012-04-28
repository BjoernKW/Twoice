<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
    	<meta content="text/html; charset=utf-8" http-equiv="content-type" />
		<meta http-equiv="Content-Script-Type" content="text/javascript" />
		<meta http-equiv="Content-Style-Type" content="text/css" />
		<meta http-equiv="Cache-Control" content="Public" />
		<meta name="author" content="${message(code:'appName', 'default':'Twoice')}" />
		<meta name="publisher" content="${message(code:'appName', 'default':'Twoice')}" />
		<meta name="copyright" content="${message(code:'appName', 'default':'Twoice')}" />
		<meta name="audience" content="${message(code:'meta.audience')}" />
		<meta name="generator" content="${message(code:'meta.generator')}" />
		<meta name="pragma" content="no-cache" />
    	<meta name="DC.Description" content="${message(code:'meta.DC.Description')}" />
		<meta name="title" content="${message(code:'meta.title', args:[message(code:'appName', 'default':'Twoice')])}" />
		<meta name="language" content="${message(code:'meta.language')}" />
		<meta name="page-topic" content="${message(code:'meta.pageTopic')}" />
		<meta name="page-type" content="${message(code:'meta.pageType')}" />
		<meta name="description" content="${message(code:'meta.description')}" />
		<meta name="keywords" content="${message(code:'meta.keywords')}" />
        <title><g:layoutTitle default="${message(code:'welcomeTo', args:[message(code:'appName')])}" /></title>
        <p:css name="main"/>
		<p:css name="jquery-ui/themes/lightness/jquery-ui-1.8.4.custom"/>
		<p:favicon src="images/favicon"/>
		<langs:resources/>
		<link rel="apple-touch-icon" href="${resource(dir:'images', file:'apple-touch-icon.png')}"/>
        <p:javascript src="application"/>
		<g:javascript library="jquery" plugin="jquery"/>
		<jqui:resources/>
		<p:javascript src="json2"/>
		<p:javascript src="org/cometd"/>
		<p:javascript src="org/cometd/ReloadExtension"/>
		<p:javascript src="jquery-cometd/jquery.json-2.2"/>
		<p:javascript src="jquery-cometd/jquery.cookie"/>
		<p:javascript src="jquery-cometd/jquery.cometd"/>
		<p:javascript src="jquery-cometd/jquery.cometd-reload"/>
		<p:javascript src="cometd"/>
		<nav:resources/>
		<g:layoutHead />
		<script type="text/javascript">

		  var _gaq = _gaq || [];
		  _gaq.push(['_setAccount', 'UA-18820970-1']);
		  _gaq.push(['_trackPageview']);

		  (function() {
		    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
		    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
		  })();

		</script>
    </head>
    <body>
        <div id="spinner" class="spinner">
            <p:image src="spinner.gif" alt="Loading" style="display: none;"/>
        </div>
		<div id="topRightMenu">
			<g:link controller="static" action="imprint"><g:message code="imprint" /></g:link><br />
			<g:link controller="static" action="contact"><g:message code="contact" /></g:link><br />
			<langs:selector langs="en_GB,de_DE"/>
		</div>
        <div id="logo" class="logo">
			<a href="${message(code:'homeURL')}">
				<p:image src="logo.png" alt="Twoice" border="0"/>
			</a>
		</div>
		<div id="mainMenu">
			<nav:render group="main" actionMatch="true"/>
		</div>
        <g:layoutBody />
        <div id="footer">
			&copy; <g:formatDate format="yyyy" date="${new Date()}"/> <a href="${message(code:'company.url')}">Twoice</a> |
			<g:link controller="start"><g:message code="home" /></g:link>
        </div>
		<p:renderDependantJavascript/>
    </body>
</html>
