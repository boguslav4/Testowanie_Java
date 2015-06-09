package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Customer;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class })
public class SellingManagerDBUnitTest {

	@Autowired
	SellingManager sellingManager;

	@Test
	@DatabaseSetup("/fullData.xml")
	@ExpectedDatabase(value = "/addCustomerData.xml", 
	assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void getClientCheck() {
        assertEquals(2, sellingManager.getAllClients().size());
        
        Customer p = new Customer();
        p.setName("Stefan");
        p.setOccupation("Kierowca Rajdowy");
        p.setPesel("43020136132");
        Calendar cal = Calendar.getInstance();
        cal.set(2015, 4, 20);
        p.setRegistrationDate(cal.getTime());
        
        sellingManager.addClient(p);
	}

	
}
