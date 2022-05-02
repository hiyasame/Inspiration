package kim.bifrost.cqupt.inspiration.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kim.bifrost.cqupt.inspiration.databinding.FragmentColorIntroduceBinding

/**
 * kim.bifrost.cqupt.inspiration.view.fragment.ColorIntroduceDialogFragment
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/4/30 21:00
 **/
class IntroduceDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentColorIntroduceBinding
    private lateinit var title: String
    private lateinit var content: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().apply {
            title = getString("title")!!
            content = getString("content")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentColorIntroduceBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            tvTitle.text = title
            tvContent.text = content
        }
    }

    companion object {
        fun newInstance(title: String, content: String): IntroduceDialogFragment {
            val args = Bundle()
                .apply {
                    putString("title", title)
                    putString("content", content)
                }
            val fragment = IntroduceDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }
}