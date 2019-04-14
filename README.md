## Selenium Automation Framwork 

### Description
In this project contains [pom.xml](https://github.com/rmihirk/Selenium-Automation-Framwork/blob/master/pom.xml) that's for project jar dependency and testNG.xml file mapping purpose
[testNG.xml](https://github.com/rmihirk/Selenium-Automation-Framwork/blob/master/src/test/resources/testng.xml) file in resource folder

### Running the tests
It's run test class whatever we have pass in testNG.xml and listeners for reporting 
```
<suite name="Test Suite">
	<listeners>
  		<listener class-name="org.uncommons.reportng.HTMLReporter" />
	  	<listenecr class-name="org.uncommons.reportng.JUnitXMLReporter" />
		  <listener class-name="listenerPages.testlistener" />
	</listeners>

	<test name="Regression suite 2">
		<classes>
			<class name="testobject.TC_00" />
		</classes>
	</test>
</suite>
```

#### testNG.xml running from pom.xml
```
  <suiteXmlFiles>
    <suiteXmlFile>src/test/resources/testng.xml</suiteXmlFile>
  </suiteXmlFiles>
```

#### chrome driver path and usedefaultlisteners is false are defined in pom.xml
#### If you want to execute testcase so simply execute pom.xml then it's directly executed testNG.xml
