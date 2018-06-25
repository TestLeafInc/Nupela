package listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

public class TestNgListeners implements IAnnotationTransformer, IRetryAnalyzer{
	
	int iMaxCount = 1;

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, 
			Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(this.getClass());		
	}

	@Override
	public boolean retry(ITestResult result) {
		if(!result.isSuccess() && iMaxCount < 2) {
			iMaxCount++;
			return true;
		}
		return false;
	}

}
