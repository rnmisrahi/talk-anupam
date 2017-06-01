package com.maatayim.talklet.screens.login;

import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * Created by Sophie on 5/24/2017.
 */
public class LoginPresenterTest {

    @Mock
    private LoginContract.View view;


    @Rule
   public MockitoRule rule = MockitoJUnit.rule();
    private LoginContract.Presenter presenter;

//    @Before
//    public void setUp() throws Exception {
//        presenter = new LoginPresenter(view);
//    }
//
//    @Test
//    public void testThatMockFacebookIsConnecting() throws Exception {
//        presenter.connect();
//
//        Mockito.verify(view).displayFacebookLoginInterface();
//    }
}