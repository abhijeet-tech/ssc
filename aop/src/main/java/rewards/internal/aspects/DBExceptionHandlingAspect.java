package rewards.internal.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import rewards.internal.exception.RewardDataAccessException;


@Aspect
@Component
public class DBExceptionHandlingAspect {
	
	public static final String EMAIL_FAILURE_MSG = "Failed sending an email to Mister Smith : ";
	
	private Logger logger = Logger.getLogger(getClass());


	@AfterThrowing(value="execution(public * rewards.internal.*.*Repository.*(..))", throwing="e")
	public void implExceptionHandling(RewardDataAccessException e) {
		// Log a failure warning
		logger.warn(EMAIL_FAILURE_MSG + e + "\n");
	}

}
