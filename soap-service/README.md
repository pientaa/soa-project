# Payment Web Service (SOAP)

In order to test `issuePayment` run following:

```bash
curl --header "content-type: text/xml" -d @issuePayment.xml http://localhost:8080/ws
```