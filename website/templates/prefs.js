
testFrameworks = ["JUnit4", "JUnit3", "Raw"];
styleElement = document.getElementById("testFrameworkStyle");

function selectTestFramework(frameworkToShow) {
  css = "";
  
  for (i in testFrameworks) {
    framework = testFrameworks[i];
    
    if (framework == frameworkToShow) {
      css += "." + framework + "{ display: block; }\n";
      css += "." + framework + " h3 { display: none; }\n";
      css += "#selector" + framework + "{ font-weight: bold; color: white; }\n";
    }
    else {
      css += "." + framework + "{ display: none; }\n";
    }
  }
  
  styleElement.innerHTML = css;
  setCookie("preferredTestFramework", frameworkToShow);
}

function restorePreferredTestFramework() {
  framework = getCookie("preferredTestFramework");
  if (framework != null) {
    selectTestFramework(framework);
  }
}

function setCookie(name, value) {
  now = new Date();
  expiry = now.setYear(now.getYear()+1);
  
  document.cookie = name + "=" + value + "; Max-Age=40000000";
}

function getCookie(name) {
  var cookies = document.cookie;
  var prefix = name + "=";
  var begin = cookies.indexOf("; " + prefix);
  
  if (begin == -1) {
    begin = cookies.indexOf(prefix);
    if (begin != 0) return null;
  }
  else {
    begin += 2;
  }
  
  var end = cookies.indexOf(";", begin);
  if (end == -1) {
    end = cookies.length;
  }
  
  return unescape(cookies.substring(begin + prefix.length, end));
}

restorePreferredTestFramework();

