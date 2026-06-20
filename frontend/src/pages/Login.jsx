import { useNavigate } from "react-router-dom";

function Login() {

  const navigate = useNavigate();

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <div className="bg-white p-8 rounded-xl shadow-lg">
        <h1 className="text-3xl font-bold mb-4">
          PricePilot
        </h1>

        <button
          onClick={() => navigate("/dashboard")}
          className="bg-blue-600 text-white px-4 py-2 rounded"
        >
          Enter App
        </button>
      </div>
    </div>
  );
}

export default Login;