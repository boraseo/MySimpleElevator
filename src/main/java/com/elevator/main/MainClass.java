package com.elevator.main;

import com.elevator.engine.ElevatorEngine;
import com.elevator.model.Elevator;
import com.elevator.model.Person;

/**
 * @author Park Hyo Jun
 * @since 2016-11-10
 */
public class MainClass {

  public static void main(String[] args) {

    // 엔진 만들고 기동
    ElevatorEngine elevatorEngine = new ElevatorEngine.Builder()
                                                      .addElevator(new Elevator("Elevator 1", 1))
                                                      .addElevator(new Elevator("Elevator 2", 5))
                                                      .addElevator(new Elevator("Elevator 3", 10))
                                                      .build();

    // 승객 탑승
    elevatorEngine.newPerson(new Person(7, 10));
    elevatorEngine.newPerson(new Person(3, 4));
    elevatorEngine.newPerson(new Person(7, 3));
    elevatorEngine.newPerson(new Person(1, 7));
    elevatorEngine.newPerson(new Person(2, 1));



  }

}
