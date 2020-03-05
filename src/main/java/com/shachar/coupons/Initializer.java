package com.shachar.coupons;
import java.util.Timer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shachar.coupons.exceptions.ApplicationException;


@Component
public class Initializer {

	@Autowired
	TimerTa timerTask;


	@PostConstruct
	public void init() throws ApplicationException{
		new Timer().schedule(timerTask, 20000);

	}
}
