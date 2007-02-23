
testFrameworks = ["JUnit4", "JUnit3", "Raw"];

styleElement = document.getElementById("testFrameworkStyle");

function selectTestFramework(frameworkToShow) {
  css = "";
  
  for (i in testFrameworks) {
    framework = testFrameworks[i];
    
    if (framework == frameworkToShow) {
      css += "." + framework + "{ display: block; }\n";
      css += "#selector" + framework + "{ font-weight: bold; }\n";
    }
    else {
      css += "." + framework + "{ display: none; }\n";
      css += "#selector" + framework + "{ font-weight: normal; }\n";
    }
  }
  
  styleElement.innerHTML = css;
  document.cookie = "preferredTestFramework="+frameworkToShow;
}

function getCookie(name) {
  var cookies = document.cookie;
  var prefix = name + "=";
  var begin = cookies.indexOf("; " + prefix);
  
  if (begin == -1) {
    begin = dc.indexOf(prefix);
    if (begin != 0) return null;
  }
  else {
    begin += 2;
  }
  
  var end = document.cookie.indexOf(";", begin);
  if (end == -1) {
    end = dc.length;
  }
  
  return unescape(dc.substring(begin + prefix.length, end));
}

function restorePreferredTestFramework() {
  framework = getCookie("preferredTestFramework");
  if (framework != null) {
    selectTestFramework(framework);
  }
}

restorePreferredTestFramework();

