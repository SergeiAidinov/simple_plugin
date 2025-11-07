Program args: -os ${target.os} -ws ${target.ws} -arch ${target.arch} -nl ${target.nl} -consoleLog
VM args: -Dorg.eclipse.swt.graphics.Resource.reportNonDisposed=true

Start as OSGI Framework:
		Program arguments: -os ${target.os} -ws ${target.ws} -arch ${target.arch} -nl ${target.nl} -consoleLog -console -nosplash
		VM arguments: -Dorg.eclipse.swt.graphics.Resource.reportNonDisposed=true -Declipse.ignoreApp=true -Dosgi.noShutdown=true