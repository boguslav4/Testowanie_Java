package notsimple;

import org.easymock.EasyMock;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import simple.*;

@RunWith(Suite.class)
//dynamicProxy nie widoczne dla Netbeansa?
@SuiteClasses({EasyMock.class, Mockito.class, Simple.class })
public class AllTests {

}
