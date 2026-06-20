import { useNavigate } from "react-router-dom";

function Dashboard() {

  const navigate = useNavigate();

  const cards = [
    {
      title: "Products",
      path: "/products"
    },
    {
      title: "Shopping Lists",
      path: "/shopping-lists"
    },
    {
      title: "Recommendations",
      path: "/recommendations"
    },
    {
      title: "Optimization",
      path: "/optimization"
    },
    {
      title: "Live Prices",
      path: "/live-prices"
    }
  ];

  return (
    <div className="min-h-screen bg-gray-100 p-10">

      <h1 className="text-4xl font-bold mb-8">
        PricePilot Dashboard
      </h1>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">

        {cards.map((card) => (

          <div
            key={card.title}
            onClick={() => navigate(card.path)}
            className="
              bg-white
              rounded-xl
              shadow-md
              p-6
              cursor-pointer
              hover:shadow-xl
              transition
            "
          >
            <h2 className="text-2xl font-semibold">
              {card.title}
            </h2>
          </div>

        ))}

      </div>

    </div>
  );
}

export default Dashboard;