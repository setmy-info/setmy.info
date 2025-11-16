import json
import os
from http.server import BaseHTTPRequestHandler, HTTPServer

# cd C:\sources\setmy.info\java-crawler\src\test\python
# py -3.14 server.py

PDF_FILENAME = "Lorem_ipsum.pdf"


class Handler(BaseHTTPRequestHandler):

    def do_GET(self):
        if self.path == "/pdf":
            self.serve_pdf()
            return

        # print console
        print("\n--- REQUEST HEADERS ---")
        for k, v in self.headers.items():
            print(f"{k}: {v}")

        # return JSON response
        self.send_response(200)
        self.send_header("Content-Type", "application/json")
        self.end_headers()
        self.wfile.write(json.dumps(dict(self.headers)).encode())

    def serve_pdf(self):
        if not os.path.exists(PDF_FILENAME):
            self.send_response(404)
            self.end_headers()
            self.wfile.write(b"PDF file not found")
            return

        self.send_response(200)
        self.send_header("Content-Type", "application/pdf")
        self.send_header("Content-Disposition", f'inline; filename="{PDF_FILENAME}"')

        pdf_size = os.path.getsize(PDF_FILENAME)
        self.send_header("Content-Length", str(pdf_size))
        self.end_headers()

        with open(PDF_FILENAME, "rb") as f:
            self.wfile.write(f.read())


server = HTTPServer(("0.0.0.0", 7171), Handler)
print("Server running at http://localhost:7171")
server.serve_forever()
