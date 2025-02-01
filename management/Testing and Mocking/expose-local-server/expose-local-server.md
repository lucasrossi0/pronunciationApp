# Expose local server

## bore

- [GitHub - ekzhang/bore: 🕳 bore is a simple CLI tool for making tunnels to localhost](https://github.com/ekzhang/bore)

> A modern, simple TCP tunnel in Rust that exposes local ports to a remote server, bypassing standard NAT connection firewalls. **That's all it does: no more, and no less.**

```bash
# Installation (requires Rust, see alternatives below)
cargo install bore-cli

# Open your shell configuration file
nano ~/.bashrc

# Add the following line at the end of the file
export PATH=$PATH:/home/albert/.cargo/bin

# Apply the changes by running
source ~/.bashrc

#  Print version
bore --version
# bore-cli 0.5.2

# On your local machine
bore local 8000 --to bore.pub
```

## ngrok

- https://ngrok.com/

> Ngrok is a cross-platform application that creates secure tunnels between a public endpoint and a locally running web service, allowing developers to expose their local servers to the internet without complex network configurations

Ngrok is particularly useful for:

- Testing webhooks and API integrations
- Sharing local development work with remote team members
- Bypassing NAT and firewall restrictions
- Quickly demonstrating local projects to clients

To use ngrok to get a public URL for your local Spring Boot application, 

1. Install ngrok if you haven't already.

2. Start your Spring Boot application locally (assuming it's running on port 8080).

3. Open a terminal and run the following command:
   
   ```bash
   ngrok http 8080
   ```

4. Ngrok will generate a public URL, which will look something like:
   
   ```bash
   https://randomlettersandnumbers.ngrok-free.app
   ```

This URL will now forward traffic to your local Spring Boot application.
    https://7b0d-139-47-34-243.ngrok-free.app

**Additional Notes:**

- The free version of ngrok provides a randomly generated URL that changes each time you restart the tunnel.

- The tunnel will remain active until you stop ngrok (use Ctrl+C in the terminal).

- For added security, you can implement authentication:
  
  ```bash
  ngrok http 8080 --basic-auth "username:password"
  ```
  
  This will prompt visitors for credentials before accessing your application.

**pause/stop**

Remember to stop ngrok when you're done to deactivate the public URL.

```bash
# f you're running ngrok as a service, use the command
ngrok service stop

#  you can use the ngrok API to stop a tunnel
http://127.0.0.1:4040/api/tunnels/:your_tunnel_name
```

### Example

```bash
# Install ngrok via Apt with the following command:
curl -sSL https://ngrok-agent.s3.amazonaws.com/ngrok.asc \
    | sudo tee /etc/apt/trusted.gpg.d/ngrok.asc >/dev/null \
    && echo "deb https://ngrok-agent.s3.amazonaws.com buster main" \
    | sudo tee /etc/apt/sources.list.d/ngrok.list \
    && sudo apt update \
    && sudo apt install ngrok


# Run the following command to add your authtoken to the default ngrok.yml configuration file.
ngrok config add-authtoken 2sP2gQO0FD8XXXXXXXXXXXJMtXVNSa57MvSxqeYHu

# Deploy your app online
ngrok http http://localhost:8080
```

## localhost.run

- https://localhost.run/docs/forever-free/

- https://localhost.run/

> **localhost.run** doesn't require any downloads or installations, making it convenient for quickly sharing your local Spring Boot application.

To expose your local Spring Boot application running on port 8080 using `localhost.run`, follow these steps:

1. Ensure your Spring Boot application is running on localhost:8080.

2. Open a terminal or command prompt.

3. Run the following command:
   
   ```bash
   ssh -R 80:localhost:8080 localhost.run
   ```

This command will create a secure tunnel from localhost.run to your local Spring Boot application.

4. After running the command, localhost.run will provide you with a public URL that you can use to access your application. The URL will look something like `https://random-subdomain.localhost.run`.
5. Share this URL with others to allow them to access your local Spring Boot application over the internet.

> Keep the terminal window open to maintain the tunnel. To stop the tunnel, simply press Ctrl+C in the terminal where the localhost.run command is running.

**annonymous**

1. Use the 'nokey' option to bypass SSH key authentication for free tunnels:

```bash
ssh -R 80:localhost:8080 nokey@localhost.run`
```

**plan**

2. If you're using a custom domain subscription, you need to set up an SSH key. Add your public key to your localhost.run account and try:

```bash
ssh -R example.com:80:localhost:8080 plan@localhost.run
```

**add host-key**

If you trust the localhost.run service, you can type 'yes' to continue. This will add the host key to your ~/.ssh/known_hosts file, and you won't see this prompt again for future connections to localhost.run[1](https://www.linode.com/docs/guides/verifying-the-authenticity-of-remote-host/).For automated scripts or to bypass this check, you can use:

```bash
ssh -o StrictHostKeyChecking=no -R 80:localhost:8080 localhost.run
```

However, be cautious when using this option as it bypasses a security feature[5](https://news.ycombinator.com/item?id=26421395).
