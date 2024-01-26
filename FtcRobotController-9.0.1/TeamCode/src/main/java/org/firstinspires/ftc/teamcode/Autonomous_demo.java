package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;


@Autonomous

public class Autonomous_demo extends LinearOpMode{

    DcMotor motorFrontLeft, motorBackLeft, motorBackRight, motorFrontRight, arm1, arm2;
    Servo intake, rotator;

    public int element_zone = 1;
    private TeamElementSubsystem teamElementDetection=null;

    boolean togglePreview = true;

    public void HardwareStart() {
        telemetry.addData("Object Creation", "Start");
        telemetry.update();

        teamElementDetection = new TeamElementSubsystem(hardwareMap);

        motorFrontLeft = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        motorBackLeft = hardwareMap.get(DcMotor.class, "motorBackLeft");
        motorFrontRight = hardwareMap.get(DcMotor.class, "motorFrontRight");
        motorBackRight = hardwareMap.get(DcMotor.class, "motorBackRight");
        arm1 = hardwareMap.get(DcMotor.class, "arm1");
        arm2 = hardwareMap.get(DcMotor.class, "arm2");
        intake = hardwareMap.get(Servo.class, "intake");
        rotator = hardwareMap.get(Servo.class, "rotator");

        telemetry.addData("Object Creation", "Done");
        telemetry.update();
        arm1.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackLeft.setDirection(DcMotor.Direction.REVERSE);
        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
        arm1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm1.setDirection(DcMotorSimple.Direction.REVERSE);
    }



    public void runOpMode() {

        HardwareStart();

        String curAlliance = "red";


        while (!opModeIsActive() && !isStopRequested()) {
            element_zone = teamElementDetection.elementDetection(telemetry);
            if (togglePreview && gamepad2.a) {
                togglePreview = false;
                teamElementDetection.toggleAverageZone();
            } else if (!gamepad2.a) {
                togglePreview = true;
            }
            intake.setPosition(0.6);
            sleep(100);


            if (gamepad1.x) {
                curAlliance = "blue";
            } else if (gamepad1.b) {
                curAlliance = "red";
            }
            teamElementDetection.setAlliance(curAlliance);
            telemetry.addData("Select Alliance (Gamepad1 X = Blue, Gamepad1 B = Red)", "");
            telemetry.addData("Current Alliance Selected : ", curAlliance.toUpperCase());


            telemetry.update();
        }











    }

    public void axisY(int ticks, double speed)
    {
        motorFrontLeft.setTargetPosition(ticks); // sets the amount of target ticks
        motorFrontRight.setTargetPosition(ticks);
        motorBackLeft.setTargetPosition(ticks);
        motorBackRight.setTargetPosition(ticks);

        motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION); // starts the motor to rotate in order to reach the target
        motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(motorFrontLeft.isBusy())
        {
            motorFrontLeft.setPower(speed);
            motorFrontRight.setPower(speed);
            motorBackRight.setPower(speed);
            motorBackLeft.setPower(speed);
        }

        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    public void axisX(int ticks)
    {
        motorFrontLeft.setTargetPosition(ticks);
        motorFrontRight.setTargetPosition(-ticks);
        motorBackLeft.setTargetPosition(-ticks);
        motorBackRight.setTargetPosition(ticks);

        motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(motorFrontLeft.isBusy())
        {
            motorFrontLeft.setPower(0.6);
            motorFrontRight.setPower(0.6);
            motorBackRight.setPower(0.6);
            motorBackLeft.setPower(0.6);
        }

        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void rotate(int ticks)
    {
        motorFrontLeft.setTargetPosition(-ticks);
        motorFrontRight.setTargetPosition(ticks);
        motorBackLeft.setTargetPosition(-ticks);
        motorBackRight.setTargetPosition(ticks);

        motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(motorFrontLeft.isBusy())
        {
            motorFrontLeft.setPower(0.6);
            motorFrontRight.setPower(0.6);
            motorBackRight.setPower(0.6);
            motorBackLeft.setPower(0.6);
        }

        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

}