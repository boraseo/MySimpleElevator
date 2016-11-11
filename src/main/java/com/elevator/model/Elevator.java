package com.elevator.model;

import com.elevator.engine.ElevatorEngine;
import com.elevator.enums.State;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Park Hyo Jun
 * @since 2016-11-10
 */
@Data
public class Elevator {
  private String name;
  private int floor;
  private State state;
  private List<Person> persons;


  public Elevator(String name, int floor) {
    this.name = name;
    this.floor = floor * 10;
    this.state = State.STOP;
    this.persons = new ArrayList<>();
  }


  /**
   * 탄 사람의 목적 층에 맞게 이동
   */
  public double moving() {

    if (persons.size() > 0
            && this.floor % 10 == 0) { // 각 층에 도착하면
      leavePersons(); // 승객 내림

      if (!persons.stream().anyMatch(p -> p.getPurpose() == this.state)) {
        // 지금 진행 중인 방향에 가는 사람이 아무도 없으면 다음 방향 설정함
        this.state = getDirection();
      }

    }

    // 가던 방향으로 계속 감
    if (State.UP == this.state) {
      if(this.floor >= ElevatorEngine.MAX_FLOOR) // 최대층 처리
        this.setState(State.STOP);
      else
        this.floor += 1;
    } else if (State.DOWN == this.state) {
      if(this.floor <= ElevatorEngine.MIN_FLOOR) // 최소층 처리
        this.setState(State.STOP);
      else
        this.floor -= 1;
    }

    return this.floor;
  }


  /*
   * 내릴 층인 사람들 내림
   */
  private void leavePersons() {
    persons.removeIf(p -> p.getToFloor() == this.floor);
    this.setState(State.STOP);
  }

  /*
   * 먼저 탄 사람의 방향에 맞게 이동 시작
   */
  private State getDirection() {
    double to = persons.stream()
            .findFirst()
            .map(Person::getToFloor)
            .orElseGet(() -> -1);


    if (to == -1) { //값이 없어서 -1.0이 되어버리면 멈춤
      return State.STOP;
    } else if (this.floor < to) {
      return State.UP;
    } else {
      return State.DOWN;
    }

  }


  /**
   * 현재 층
   *
   * @return the double
   */
  public double getFloor() {
    return this.floor;
  }


  /**
   * 엘리베이터에 사람 탑승
   *
   * @param person the person
   * @return the boolean
   */
  public boolean addPerson(Person person) {
    if (persons.size() < 20) {
      this.persons.add(person);
      return true;
    } else
      return false;
  }


}
