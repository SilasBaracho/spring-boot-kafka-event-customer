package sb.csm.event.create.customer.integration.emailManagerApi.request

import kafka.event.create.customer.EventCreateCustomer

data class EmailManagerApiRequest(
    val ownerRef: String,
    val emailFrom: String,
    val emailTo: String,
    val subject: String,
    val text: String,
): java.io.Serializable{

    constructor(customer: EventCreateCustomer): this(
        ownerRef = "event-create-customer",
        emailFrom = "silasbaracho@gmail.com",
        emailTo = customer.getEmail(),
        subject = "Verify Account",
        text = """<!DOCTYPE html>
        <html lang="pt-br">
        <head>
            <meta charset="UTF-8"> <meta http-equiv="X-UA-Compatible" content="IE=edge"> <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <style>
                body { font-family: Arial, sans-serif; line-height: 1.6; margin: 0; padding: 0; }
                .container { max-width: 600px; margin: 0 auto; padding: 20px; }
                .button { display: inline-block; background-color: #007BFF; color: #ffffff; padding: 10px 20px; text-decoration: none; border-radius: 5px; }
            </style>
        </head>
        <body>
            <div class="container">
                <h1>Hello, thank you for creating your account!</h1>
                <p>Just one more step to go. Click the button below to confirm your registration:</p>
                <a class="button" href="#" id="verifyAccountLink">Verify Account</a>
            </div>
            <script src="https://code.jquery.com/jquery-3.6.0.min.js%22%3E</script>
            <script>
                ${'$'}(document).ready(function () {
                    ${'$'}("#verifyAccountLink").on("click", function (event) {
                        event.preventDefault();
                        var data = {
                            method: 'PUT',
                            headers: { contentType: "charset=UTF-8" },
                            mode: 'no-cors',
                            cache: 'default'
                        };

                        fetch('http://localhost:8080/v1/customer/verify-account?email=${customer.getEmail()}&otp=${customer.getOtp()}', data)
                            .then( 
                                function(response){  }
                            );
                    });
                });
            </script>
        </body>
        </html>"""
    )
}
