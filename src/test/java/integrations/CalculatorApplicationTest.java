package integrations;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import org.apache.thrift.protocol.TProtocolFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.facebook.nifty.client.HttpClientConnector;
import com.facebook.swift.codec.ThriftCodecManager;
import com.facebook.swift.service.ThriftClientManager;
import com.xiaojiuwo.Application;
import com.xiaojiuwo.services.TCalculatorService;
import com.xiaojiuwo.services.TDivisionByZeroException;
import com.xiaojiuwo.services.TOperation;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@EnableAutoConfiguration(exclude = {DispatcherServletAutoConfiguration.class ,DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class CalculatorApplicationTest {

    @Autowired
    TProtocolFactory tProtocolFactory;
    
    @Autowired
    ThriftCodecManager thriftCodecManager;

    @Value("${local.server.port}")
    protected int port;

    protected TCalculatorService client;

    @Before
    public void setUp() throws Exception {
        HttpClientConnector connector = new HttpClientConnector(URI.create("http://localhost:" + port + "/thrift/"));

        ThriftClientManager clientManager = new ThriftClientManager(thriftCodecManager);
        client = clientManager.createClient(connector, TCalculatorService.class).get();
    }

    @Test
    public void testAdd() throws Exception {
        assertEquals(5, client.calculate(2, 3, TOperation.ADD));
    }

    @Test
    public void testSubtract() throws Exception {
        assertEquals(3, client.calculate(5, 2, TOperation.SUBTRACT));
    }

    @Test
    public void testMultiply() throws Exception {
        assertEquals(10, client.calculate(5, 2, TOperation.MULTIPLY));
    }

    @Test
    public void testDivide() throws Exception {
        assertEquals(2, client.calculate(10, 5, TOperation.DIVIDE));
    }

    @Test(expected = TDivisionByZeroException.class)
    public void testDivisionByZero() throws Exception {
        client.calculate(10, 0, TOperation.DIVIDE);
    }
}