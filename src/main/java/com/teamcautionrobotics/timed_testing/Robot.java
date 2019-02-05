/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.teamcautionrobotics.timed_testing;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private VictorSP driveLeft, driveRight;
  private Joystick joystickLeft, joystickRight;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code, because why use constructors?
   */
  @Override
  public void robotInit() {
    driveLeft = new VictorSP(0);
    driveRight = new VictorSP(1);

    joystickLeft = new Joystick(0);
    joystickRight = new Joystick(1);
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    driveLeft.set(joystickLeft.getY());
    // Negative because the drive train has mirror symmetry
    driveRight.set(-joystickRight.getY());
  }
}
