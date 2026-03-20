import os
import subprocess
import time
import json

# This script verifies the MCP server's decryption tool
# It starts the server, then uses a simulated MCP client (or simple direct test)
# Since running a full MCP client might be complex here,
# we'll test the server logic directly by importing or running a simplified version.

def test_decryption_logic():
    print("Testing decryption logic directly...")
    from cryptography.fernet import Fernet

    key = "B2HBAf9aJczoLQhYVJuguE4Dd-5AOECFp3RR9vRgrzY="
    os.environ["DECRYPTION_KEY"] = key

    # Import the tools from our server
    # We need to make sure the server file is in the path
    import sys
    sys.path.append(os.path.join(os.getcwd(), "src"))
    from server import decrypt_md_content, decrypt_md_file

    # Test content decryption
    encrypted_content = "gAAAAABpvQzF8iWmNsqUzr0xPCIPNrtRsx7AA2fTNgQ9TkZVuhBXmj728OXZq8BZeisJKMzNwpBJWwXyM8RoFOn4d_rDKkd4NGD8dJ84M6M12AEsDNLfnFNV3922g7XYtaESV7LsaYup0tPpokpgggytzbt4IkJ6CJHox9d0cVV_v8ZWyOM6WyQ="
    decrypted = decrypt_md_content(encrypted_content)
    print(f"Decrypted content: {decrypted}")

    if "# My Secret AI Profile" in decrypted:
        print("SUCCESS: Content decryption verified.")
    else:
        print("FAILURE: Content decryption failed.")

    # Test file decryption
    test_file = "secret_profile.md"
    if os.path.exists(test_file):
        decrypted_file = decrypt_md_file(test_file)
        print(f"Decrypted file content: {decrypted_file}")
        if "# My Secret AI Profile" in decrypted_file:
            print("SUCCESS: File decryption verified.")
        else:
            print("FAILURE: File decryption failed.")
    else:
        print(f"FAILURE: {test_file} not found.")

if __name__ == "__main__":
    test_decryption_logic()
