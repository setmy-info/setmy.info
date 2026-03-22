import os
import base64
from cryptography.fernet import Fernet

def generate_key():
    key = Fernet.generate_key()
    return key.decode()

def encrypt_content(key_str, content):
    fernet = Fernet(key_str.encode())
    encrypted = fernet.encrypt(content.encode())
    return encrypted.decode()

def main():
    # 1. Generate a key
    key = generate_key()
    print(f"DECRYPTION_KEY={key}")

    # 2. Sample content
    content = "# My Secret AI Profile\n\nThis is a highly secret AI profile content."

    # 3. Encrypt
    encrypted_content = encrypt_content(key, content)

    # 4. Save to an encrypted file
    test_file = "secret_profile.md"
    with open(test_file, "w", encoding="utf-8") as f:
        f.write(encrypted_content)

    print(f"Created encrypted file: {test_file}")
    print(f"Encrypted content: {encrypted_content}")

if __name__ == "__main__":
    main()
