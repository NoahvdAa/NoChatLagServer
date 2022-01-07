# NoChatLagServer

Downloads: [SpigotMC](https://www.spigotmc.org/resources/nochatlagserver.99046/) | [GitHub Releases](https://github.com/NoahvdAa/NoChatLagServer/releases/tag/1.0.0)

NoChatLagServer fixes [MC-247973](https://bugs.mojang.com/projects/MC/issues/MC-247973) by setting the sender-uuid to that of the server for every chat message.
Since messages sent by the server itself bypass the block check, this will prevent the lag spikes from happening, **with the side effect of completely breaking the blocking system**.

If you don't want the block list broken, check out my client-side fabric mod, [NoChatLag](https://modrinth.com/mod/nochatlag).

Requires [ProtocolLib](https://www.spigotmc.org/resources/protocollib.1997/)
