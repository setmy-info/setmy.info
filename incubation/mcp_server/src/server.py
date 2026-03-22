import os
import base64
from cryptography.fernet import Fernet
from fastmcp import FastMCP
from pydantic import Field

# Initialize FastMCP server
mcp = FastMCP("Crypto-MD-Server")

# Get decryption key from environment variable
# The key should be a 32-byte base64 encoded string
DECRYPTION_KEY = os.environ.get("DECRYPTION_KEY")

def get_fernet():
    if not DECRYPTION_KEY:
        raise ValueError("DECRYPTION_KEY environment variable is not set")
    try:
        return Fernet(DECRYPTION_KEY.encode())
    except Exception as e:
        raise ValueError(f"Invalid DECRYPTION_KEY: {e}")

@mcp.tool()
def decrypt_md_content(encrypted_content: str) -> str:
    """
    Decrypts the provided encrypted markdown content.

    Args:
        encrypted_content: The base64 encoded encrypted string.
    """
    fernet = get_fernet()
    try:
        decrypted_bytes = fernet.decrypt(encrypted_content.encode())
        return decrypted_bytes.decode('utf-8')
    except Exception as e:
        return f"Error decrypting content: {str(e)}"

@mcp.tool()
def decrypt_md_file(file_path: str) -> str:
    """
    Reads an encrypted .md file and returns the decrypted content.

    Args:
        file_path: The path to the encrypted .md file.
    """
    if not os.path.exists(file_path):
        return f"Error: File not found at {file_path}"

    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            encrypted_content = f.read().strip()

        return decrypt_md_content(encrypted_content)
    except Exception as e:
        return f"Error reading file: {str(e)}"

if __name__ == "__main__":
    mcp.run()
