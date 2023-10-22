<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Sending Email with Freemarker HTML Template Example</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <!-- use the font -->
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            font-size: 20px;
        }
    </style>
</head>
    <body style="margin: 0; padding: 0;">

        <table border="0" cellpadding="0" cellspacing="0" width="1100" style="border-collapse: collapse;">
            <tr>
                <td align="center" bgcolor="#eaeaea" style="padding: 10px 0 10px 0;">
                    <p>
                        <table>
                            <tr>
                                <td>
                                    <img src="https://cdn-icons-png.flaticon.com/512/295/295172.png" alt="http://consol.mapmox.com" style="display: block;max-width:40px;" />
                                </td>

                                <td>
                                    Daily Coding Problem
                                </td>
                            </tr>
                        </table>


                    </p>

                </td>
                <!-- <td align="center" bgcolor="#eaeaea" style="padding: 40px 0 30px 0;">
                    Daily Coding Problem
                </td> -->
            </tr>

            <tr>
                <td style="padding: 10px 30px 5px 20px;">
                    <h3>Good morning!</h3>
                    <h3>Here's your coding interview problem for today.</h3>
                    <h3>Problem Statement : ${problemName}</h3>
                    <h3>Link : ${problemLink}</h3>
                </td>
            </tr>

            <tr>
                <td style="padding: 2px 0px 5px 0px;">
                    <hr width="1100">
                    <h3>If you liked this problem, feel free to forward it along so they can
                        <a href="https://daily-coding-problem-37a9a.web.app/">subscribe here!</a>
                        As always, shoot us an email if there's anything we can help with!
                    </h3>
                </td>
            </tr>

            <tr>
                <td>
                    <hr width="1100">
                    <p style="font-size: small;">No more? <a href="https://daily-coding-problem-37a9a.web.app/">Snooze or unsubscribe.</a></p>
                </td>
            </tr>

            <tr>
                <td align="center" bgcolor="#eaeaea" style="padding: 20px 0 20px 0;">
                    <p style="font-size: small;">© 2019 Daily Coding Problem. All rights reserved.</p>
                </td>
            </tr>

            <!-- <#--<tr>
                <td bgcolor="#777777" style="padding: 30px 30px 30px 30px;">
                    <p>${signature}</p>
                    <p>${location}</p>
                </td>
            </tr>-->
        </table>

    </body>
</html>