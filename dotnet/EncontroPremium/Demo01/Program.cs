using OllamaSharp;

var uri = new Uri("http://localhost:11434");
var ollama = new OllamaApiClient(uri);

var models = await ollama.ListLocalModelsAsync();

foreach (var model in models)
{
    Console.WriteLine(model.Name);
}

ollama.SelectedModel = "phi3:latest";

var chat = new Chat(ollama);

var prompt = "Answer in just one sentence.";
prompt += Environment.NewLine;
prompt += Console.ReadLine();

await foreach (var answer in chat.SendAsync(prompt))
{
    Console.Write(answer);
}

Console.WriteLine();
Console.WriteLine("--");