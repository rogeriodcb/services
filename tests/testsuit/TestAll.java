package testsuit;

import org.junit.runners.Suite;

import csv_manager.CSVManagerTest;
import sincronizacaoreceita.SincronizacaoReceitaTest;

import org.junit.runner.RunWith;
/**
* <b>test suit</b><br><br>
* Test all test cases made.
* <p>Modified by</p>
* @author      Rogério de C. Brito <krcbrito@gmail.com>
* @date 12/13/2020
*/
@RunWith(Suite.class)
@Suite.SuiteClasses({CSVManagerTest.class, SincronizacaoReceitaTest.class})
public class TestAll {
  //nothing
}