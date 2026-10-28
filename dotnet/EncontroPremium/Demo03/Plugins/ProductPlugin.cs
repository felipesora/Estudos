using System.ComponentModel;
using Microsoft.SemanticKernel;

namespace Demo03.Plugins;

public class ProductPlugin
{
    private readonly List<Product> _products = [
        new(1, "Mousepad", true, 10),
        new(2, "Mouse Gamer", true, 5),
        new(3, "Teclado Gamer", true, 3),
        new(4, "Capa Monitor", false, 1),
        new(5, "Monitor Gamer", true, 8),
    ];

    [KernelFunction("get_products")]
    [Description("Get a list of products and their current status")]
    public async Task<List<Product>> GetProductsAsync()
    {
        await Task.Delay(1);
        return _products;
    }

    [KernelFunction("get_state")]
    [Description("Gets the state od a particular product")]
    public async Task<Product?> GetStateAsync([Description("The ID of the product")] int id)
    {
        await Task.Delay(1);
        return _products.FirstOrDefault(x => x.Id == id);
    }

    [KernelFunction("change_state")]
    [Description("Changes the state of the product")]
    public async Task<Product?> ChangeStateAsync(
        [Description("The ID of the product")] int id,
        [Description("The content of the product to be modified")] Product model)
    {
        await Task.Delay(1);
        var product = _products.FirstOrDefault(x => x.Id == id);

        if (product == null)
            return null;

        product = new Product(model.Id, model.Title, model.IsActive, model.QuantityOnHand);
        return product;
    }
}

public record Product(int Id, string Title, bool IsActive, int QuantityOnHand);